package de.htwg.scalamusic.music

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import scala.language.postfixOps

class MusicSeqSpec extends AnyWordSpec with Matchers:

    "MusicSeq is used for nesting and" should {
        val tune = Tune(c16, g16, e16, g16)
        val line = Line(c16 -, tune, tune)
        val track = Track(c16, g16, e16, g16, tune, line)
        "a tune should have a nice String representation" in {
            tune.toString should be("(c1/16, g1/16, e1/16, g1/16)")
        }
        "a line should have a nice String representation" in {
            line.toString should be("[c1/16, (c1/16, g1/16, e1/16, g1/16), (c1/16, g1/16, e1/16, g1/16)]")
        }
        "a track shoule have a nice String representation" in {
            track.toString should be(
              "{c16, g16, e16, g16, (c16, g16, e16, g16), [c16, (c16, g16, e16, g16), (c16, g16, e16, g16)], {c16, g16, e16, g16, (c16, g16, e16, g16), [c16, (c16, g16, e16, g16), (c16, g16, e16, g16)]}}"
            )
        }
        "nest Keys in Tunes" in {
            tune.play()
            play(tune)
        }
        "nest Tunes in Lines" in {
            line.play()
            play(line)
        }
        "nest Lines in Tracks" in {

            track.play()
            play(track)
        }
        "and nest in any other combination, but the above is the intention" in {
            val mix = Track(c16, g16, e16, g16, tune, line)
            mix.play()
            play(mix)
        }

    }
