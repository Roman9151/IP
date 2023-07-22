package com.app.decoder

import com.comcast.ip4s.IpAddress
import io.circe.*
import io.circe.generic.semiauto.*
import io.circe.syntax.*

case class IPResponse(ip: IpAddress) {}

object IPResponse {
  implicit val addressDecoder: Decoder[IpAddress] = addressCursor =>
    for {
      ip <- addressCursor.get[IpAddress]("ip")
    } yield ip
}