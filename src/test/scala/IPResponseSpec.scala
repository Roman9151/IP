import app.decoder.IPResponse
import com.comcast.ip4s.IpAddress
import org.scalatest.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.should

object IPTest extends AnyFlatSpec {
  private val ip = IpAddress.fromString("").get
  private val ipResponse = IPResponse(ip)

  "A Stack" should "pop values in last-in-first-out order" in {
   ipResponse.ip.toString should be ("0.0.0.0")
  }


}
