package com

import cats.effect.{IO, Resource}
import com.comcast.ip4s.IpAddress
import org.http4s.HttpRoutes
import org.http4s.client.Client
import org.http4s.ember.client.EmberClientBuilder
import org.http4s.implicits.*
import org.ipify.getter.IPResponse
import org.scalatest.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.matchers.should.Matchers.*

class IPResponseSpec extends AnyFlatSpec with Matchers {

  "An IPResponse" should "convert the IpAddress to string correctly" in {
    val validIP = IpAddress.fromString("192.168.1.1").get
    val validResponse = IPResponse(validIP)
    validResponse.ip.toString should be("192.168.1.1")
  }


}

