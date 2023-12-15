package de.htwg.scalamusic.music

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import scala.language.postfixOps

class LineSpec extends AnyWordSpec with Matchers {
    val line = Line(c, d, e, f)
    "A Line" should {
        "have a nice String representation" in {
            line.toString should be("[c, d, e, f]")
        }
        "have a nice String representation with a repeat" in {
            (line * 2).toString should be("[c, d, e, f, c, d, e, f]")
        }
        "have a nice String representation with a pattern" in {
            (line * Pattern(1, 2)).toString should be("[c, d, d, e, f]")
        }
        "have a nice String representation with a pattern and a repeat" in {
            (line * Pattern(1, 2) * 2).toString should be("[c, d, d, e, f, c, d, d, e, f]")
        }
        "have a nice String representation with a repeat and a pattern" in {
            (line * 2 * Pattern(1, 2)).toString should be("[c, d, d, e, f, c, d, d, e, f]")
        }
    }
}
