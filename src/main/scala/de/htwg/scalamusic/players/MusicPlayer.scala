package de.htwg.scalamusic.players

import akka.actor.typed.{Behavior, ActorSystem, ActorRef, Props}
import akka.actor.typed.scaladsl.Behaviors

import de.htwg.scalamusic.music._
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.concurrent.ExecutionContext.Implicits.global
import de.htwg.scalamusic.midi._
import akka.actor.typed.scaladsl.TimerScheduler

object MusicPlayer:
    sealed trait PlayerCommand
    final case class Tick(tickNum: Int) extends PlayerCommand
    final case class PlayNow(music: List[Option[Music]]) extends PlayerCommand
    final case class PlayAt(tick: Int, music: List[Option[Music]]) extends PlayerCommand

    def apply(): Behavior[PlayerCommand] =
        Behaviors.withTimers(timers => {
            new MusicPlayer(timers, Piano).play()
        })

case class MusicPlayer(timers: TimerScheduler[MusicPlayer.PlayerCommand], instrument: Instrument):

    var tickList: List[Option[Music]] = List()

    private def play(tickList: List[Option[Music]] = List()): Behavior[MusicPlayer.PlayerCommand] =
        Behaviors.receiveMessage[MusicPlayer.PlayerCommand] {
            case MusicPlayer.Tick(tickNum)      => playNextKey
            case MusicPlayer.PlayNow(_tickList) => Behaviors.same // tickList = _tickList
            case MusicPlayer.PlayAt(tick, _tickList) =>
                Behaviors.same // tickList = (1 until tick).toList.map(x => None) ::: _tickList
        }

    def playNextKey: Behavior[MusicPlayer.PlayerCommand] = {
        if (tickList != Nil) {
            tickList.head match {
                case None           =>
                case Some(key: Key) => playKey(key)
                // case Some(chord: Chord) =>
                //     chord.set.foreach(key => playKey(key.copy(ticks = chord.ticks, volume = chord.volume)))
                // case _ =>
            }
            tickList = tickList.tail
        }
        Behaviors.same
    }
    def playKey(key: Key): Behavior[MusicPlayer.PlayerCommand] = {
        instrument.midiPlayer.start(key.midiNumber, key.volume)
        timers.startSingleTimer(key, MusicPlayer.Tick(key.ticks), Context.tickduration * key.ticks)
        instrument.midiPlayer.stop(key.midiNumber, key.volume)
        Behaviors.same
    }

// trait MusicPlayer {
//     val actor: ActorRef
//     def play(music: Music): Unit
//     def playAt(tick: Int, music: Music): Unit
// }

// case class MusicPlayerImpl(musicActor: ActorRef) extends MusicPlayer {
//     val actor = musicActor
//     def play(music: Music): Unit = musicActor ! PlayNow(music.toTickList)
//     def playAt(tick: Int, music: Music): Unit = musicActor ! PlayAt(tick, music.toTickList)
// }

// val system = ActorSystem("ScalaMusicActorSystem")

// def player(instrument: Instrument, name: String = ""): MusicPlayer = {
//     val actor = system.actorOf(
//       Props(MusicActor(instrument)),
//       name = if (name == "") instrument.toString + "Actor" else name + "Actor"
//     )
//     TypedActor(system).typedActorOf(
//       TypedProps(classOf[MusicPlayerImpl], new MusicPlayerImpl(actor)),
//       name = if (name == "") instrument.toString + "Player" else name + "Player"
//     )
// }

// val PianoPlayer: MusicPlayer = MusicPlayer(timers, Piano)
// val MarimbaPlayer = MusicPlayer(Marimba)
// val XylophonePlayer = MusicPlayer(Xylophone)
// val OrganPlayer = MusicPlayer(Organ)
// val GuitarPlayer = MusicPlayer(Guitar)
// val BassPlayer = MusicPlayer(Bass)
// val ViolinPlayer = MusicPlayer(Violin)
// val CelloPlayer = MusicPlayer(Cello)
// val TrumpetPlayer = MusicPlayer(Trumpet)
// val TubaPlayer = MusicPlayer(Tuba)
// val HornPlayer = MusicPlayer(Horn)
// val SaxPlayer = MusicPlayer(Sax)
// val OboePlayer = MusicPlayer(Oboe)
// val ClarinetPlayer = MusicPlayer(Clarinet)
// val FlutePlayer = MusicPlayer(Flute)
// val DrumPlayer = MusicPlayer(Drum)

//val Conductor = system.actorOf(Props(classOf[Conductor]), "Conductor")
