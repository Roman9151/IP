package org.ipify.getter

import scala.concurrent.duration.*
import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits.*
import com.comcast.ip4s.IpAddress
import io.circe.parser.decode
import org.http4s.client.Client
import org.http4s.blaze.client.BlazeClientBuilder
import org.http4s.implicits.uri
import org.ipify.getter.IPResponse.*
import retry.*
import retry.RetryDetails.{GivingUp, WillDelayAndRetry}

import java.io.IOException

object Main extends IOApp.Simple {
  private val APIFY = uri"https://api.ipify.org/?format=json"

  private val retryPolicyWithLimit = RetryPolicies.limitRetries[IO](4)
  private val retryPolicyWithDelay = RetryPolicies.constantDelay[IO](500.millis)
  private val limitWithDelayPolicy = retryPolicyWithLimit.join(retryPolicyWithDelay)

  private def logError(err: Throwable, details: RetryDetails): IO[Unit] = details match {
    case WillDelayAndRetry(nextDelay: FiniteDuration,
    retriesSoFar: Int,
    cumulativeDelay: FiniteDuration) =>
      IO.println(s"Failed to execute api call due to ${err.getMessage}. So far we have retried $retriesSoFar times.")
    case GivingUp(totalRetries: Int, totalDelay: FiniteDuration) =>
      IO.println(s"Giving up after $totalRetries retries")
  }

  private def checkIOException(t: Throwable): IO[Boolean] = t match
    case _: IOException => IO.pure(true)
    case _ => IO.pure(false)

  private def getIP(client: Client[IO]): IO[IPResponse] = {
    val res = client
      .expect[IPResponse](APIFY)
    retryingOnSomeErrors[IPResponse](
      policy = limitWithDelayPolicy,
      isWorthRetrying = checkIOException,
      onError = logError
    )(res)
  }

  private def execute: IO[IPResponse] = BlazeClientBuilder
    .apply[IO]
    .resource
    .use(getIP)


  override def run: IO[Unit] = execute.flatMap(response => IO.println(response.ip)).as(ExitCode.Success)

}