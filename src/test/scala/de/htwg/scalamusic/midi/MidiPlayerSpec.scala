package de.htwg.scalamusic.midi

import de.htwg.scalamusic.music.Key

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import scala.language.postfixOps
import scala.concurrent.duration._

class MidiPlayerSpec extends AnyWordSpec:

    "A MidiPlayer" should {
        val midiplayer = new MidiPlayer

        "play Keys encoded in midiKeys (0-127, c is key 60) for a duration in milliseconds" in {
            midiplayer.play(key = 60, duration = 100 milliseconds)
            Thread.sleep(100)
        }
        "play a set of Keys simultaneously, needed for chords" in {
            val set = Set(new Key(60), new Key(64), new Key(67))
            midiplayer.play(set, 75)
        }
        "start and stop a key with some delay inbetween. This is needed to play several keys at once." in {
            midiplayer.start(60)
            Thread.sleep(100)
            midiplayer.stop(60)
        }
        "be able to change instrument" in {
            midiplayer.changeToInstrument(40)
            midiplayer.play(60, 100 milliseconds)
            Thread.sleep(200)
        }

    }
