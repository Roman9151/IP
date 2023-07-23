package org.ipify.getter

import cats.effect.IO
import com.comcast.ip4s.IpAddress
import io.circe.*
import io.circe.generic.semiauto.*
import io.circe.syntax.*
import org.http4s.EntityDecoder
import org.http4s.circe.jsonOf

case class IPResponse(ip: IpAddress)

object IPResponse {
  implicit val addressDecoder: Decoder[IpAddress] = Decoder.decodeString.emap(ip => IpAddress.fromString(ip).toRight("Invalid IP address"))
  implicit val responseDecoder: Decoder[IPResponse] = deriveDecoder[IPResponse]
  implicit val entityDecoder: EntityDecoder[IO, IPResponse] = jsonOf[IO, IPResponse]
}