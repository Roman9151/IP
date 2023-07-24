package com

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import io.circe.parser.*
import io.circe.generic.auto.*
import org.ipify.getter.IPResponse

class IPResponseSpec extends AnyFlatSpec with Matchers {

  "IPResponse" should "be decoded via io.circe.Decoder" in {
    val input = """{"ip": "192.168.1.1"}"""
    val result = parse(input).flatMap(_.as[IPResponse])
    result shouldBe a[Right[_, _]]
  }

  it should "fail decoding with invalid IP" in {
    val input = """{"ip": "999.168.1.1"}"""
    val result = parse(input).flatMap(_.as[IPResponse])
    result shouldBe a[Left[_, _]]
  }

  it should "fail decoding without ip field" in {
    val input = """{"address": "192.168.1.1"}"""
    val result = parse(input).flatMap(_.as[IPResponse])
    result shouldBe a[Left[_, _]]
  }
}
