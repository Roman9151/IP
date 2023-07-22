package com.app.request

import zio._

import zio.http.Client

object SimpleClient extends ZIOAppDefault {
  private val url = "https://api.ipify.org/?format=json"

  private val program = for {
    res  <- Client.request(url)
    data <- res.body.asString
    _    <- Console.printLine(data)
  } yield ()

  override val run = program.provide(Client.default)

}

