package org.ipify.getter

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits.*
import com.comcast.ip4s.IpAddress
import io.circe.parser.decode
import org.http4s.client.Client
import org.http4s.blaze.client.BlazeClientBuilder
import org.http4s.implicits.uri
import org.ipify.getter.IPResponse.*

object Main extends IOApp.Simple {
  private val APIFY = uri"https://api.ipify.org/?format=json"

  private def getIP(client: Client[IO]): IO[IPResponse] = {
    client
      .expect[IPResponse](APIFY)
      .handleErrorWith { error =>
        IO.raiseError(new RuntimeException(s"Failed to get IP: ${error.getMessage}"))
      }
  }

  private def execute: IO[IPResponse] = BlazeClientBuilder
    .apply[IO]
    .resource
    .use(getIP)


  override def run: IO[Unit] = execute.flatMap(response => IO.println(response.ip)).as(ExitCode.Success)

}