import com.app.decoder.IPDecoder
import com.comcast.ip4s.IpAddress
import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class IPResponseSpec extends AnyFlatSpec {

  "An IPResponse" should "convert the IpAddress to string correctly" in {
    val validIP = IpAddress.fromString("192.168.1.1").get
    val validResponse = IPDecoder(validIP)
    validResponse.ip.toString should be("192.168.1.1")
  }
}

