package org.ipify.getter

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits.*
import com.comcast.ip4s.IpAddress
import io.circe.parser.decode
import org.http4s.client.Client
import org.http4s.ember.client.EmberClientBuilder
import org.http4s.implicits.uri
import org.ipify.getter.IPResponse
import org.ipify.getter.IPResponse.*

object Main extends IOApp.Simple {
  private val IPIFY = uri"https://api.ipify.org/?format=json"

  private def getIP(client: Client[IO]): IO[IPResponse] = {
    client
      .expect[IPResponse](IPIFY)
      .handleErrorWith { error =>
        IO.raiseError(new RuntimeException(s"Failed to get IP: ${error.getMessage}"))
      }
  }

  private def execute: IO[IPResponse] = EmberClientBuilder
    .default[IO]
    .build
    .use(getIP)


  override def run: IO[Unit] = execute.flatMap(IO.println)

}