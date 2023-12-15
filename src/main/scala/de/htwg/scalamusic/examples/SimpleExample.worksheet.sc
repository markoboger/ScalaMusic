import de.htwg.scalamusic.music._
import de.htwg.scalamusic.music.Context._
import scala.language.postfixOps

2 + 4
Key(60).play(instrument = Piano, Context.volume)
play(Key(60))

c.play(instrument = Piano, volume = 100)
play(c)
c.toString
c.volume
c.pattern
c.repeat
c.ticks
c.octave
c.duration

//play(c, d, e, f, g, a, b, c +)

val tonLeiter = Tune(c, d, e, f, g, a, b, c +)
tonLeiter.toString
tonLeiter.play(Piano, volume = 100)
play(tonLeiter)

val tune = Tune(c16, g16, e16, g16)
val line = Line(c16 -, tune, tune)
line.toString
