package de.htwg.scalamusic.music

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import scala.language.postfixOps

class ChordSpec extends AnyWordSpec with Matchers:

    "A Chord" should {
        "be easy to create" in {
            Chord(c, e, g)
            Chord(c, e, g, ais)
            (c, e, g)
            (c, e, g, ais)
            c.maj
        }
        "have a concise String representation for all well known chords" in {
            c.maj.toString should be("Cmaj")
        }
    }
    "Playing a Chord" should {
        "be easy from the REPL or Worksheet" in {
            play(c16.maj)
        }
        "should also be possible on the Chord" in {
            c16.maj.play()
        }
    }
