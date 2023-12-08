package de.htwg.scalamusic.players

import akka.actor.typed.{Behavior, ActorSystem, ActorRef, Props}
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.Scheduler
import scala.concurrent.duration._
import scala.language.postfixOps
import de.htwg.scalamusic.music._

object Conductor:
    sealed trait ConductorCommand
    final case class Start() extends ConductorCommand
    final case class Stop() extends ConductorCommand
    final case class Tick(tickNum: Int) extends ConductorCommand
    final case class PlayNow(music: List[Option[Music]]) extends ConductorCommand
    final case class PlayAt(tick: Int, music: List[Option[Music]]) extends ConductorCommand
    final case class Add(player: MusicPlayer) extends ConductorCommand

    def apply(): Behavior[ConductorCommand] =
        Behaviors.withTimers(timers => new Conductor(List.empty).conduct(List.empty))

case class Conductor(players: List[MusicPlayer] = List.empty):
    import Conductor._
    private def conduct(players: List[MusicPlayer]): Behavior[ConductorCommand] = {
        Behaviors.receiveMessage {
            case Start() =>
                println("Conductor Started")
                conduct(players)
            case Stop() =>
                println("Conductor Stopped")
                stop()
                Behaviors.stopped
            case Add(player) =>
                println("Conductor Added Player")
                conduct(player :: players)
            case Tick(tickNum) =>
                // players.foreach(_ ! Tick(tickNum))
                println("Conductor Tick")
                conduct(players)
            case _ =>
                println("Conductor something else")
                Behaviors.same
        }
    }
    var tickNum: Int = 0
    // val cancellable = system.scheduler.schedule(0 milliseconds, Context.tickduration, self, Tick(tickNum))
    def stop(): Unit = ??? // cancellable.cancel()
