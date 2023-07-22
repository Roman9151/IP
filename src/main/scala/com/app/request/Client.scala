package com.app.request

import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.ember.client.EmberClientBuilder
import org.http4s.client.Client
import org.typelevel.log4cats.LoggerFactory
import org.typelevel.log4cats.slf4j.Slf4jFactory

object Client extends IOApp {
  private val ROOT = "https://api.ipify.org/"
  private val PATH = "?format=json"

  private def getIP(client: Client[IO]): IO[String] =
    client
      .expect[String](s"$ROOT$PATH")
      .handleErrorWith { error =>
        IO.raiseError(new RuntimeException(s"Failed to get IP: ${error.getMessage}"))
      }


  private def execute: IO[String] = EmberClientBuilder
    .default[IO]
    .build
    .use(client => getIP(client))




  override def run(args: List[String]): IO[ExitCode] = execute.as(ExitCode.Success)


} 