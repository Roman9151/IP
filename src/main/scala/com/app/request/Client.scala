package com.app.request

import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.ember.client.EmberClientBuilder
import org.http4s.client.Client

object Client extends IOApp {

  private def getIP(client: Client[IO]): IO[String] = {
    val url = EndPoint(root = "https://api.ipify.org", path = "/?format=json").getUrl
    client
      .expect[String](url)
      .handleErrorWith { error =>
        IO.raiseError(new RuntimeException(s"Failed to get IP: ${error.getMessage}"))
      }
  }

  private def execute: IO[String] = EmberClientBuilder
    .default[IO]
    .build
    .use(client => getIP(client))

  private def parseResponse: IO[Unit] = {
    execute.flatMap(IO.println)
  }

  override def run(args: List[String]): IO[ExitCode] = parseResponse.as(ExitCode.Success)
}