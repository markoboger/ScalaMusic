package de.htwg.scalamusic

import players._
import Conductor._
import music._

import akka.actor.typed.{ActorRef, ActorSystem, Behavior}
import akka.actor.typed.scaladsl.Behaviors

@main
def run(): Unit =
    println("This is ScalaMusic!")

    val system: ActorSystem[Conductor.ConductorCommand] = ActorSystem(Conductor().starttimer(), "ScalaMusic")
    val conductor: ActorRef[Conductor.ConductorCommand] = system
    // conductor ! Add(PianoPlayer)
    // Conductor ! Add(CelloPlayer)
    // conductor ! Start()
    conductor ! Tick(1)

    val voice1 = Tune(e2, e2, |, f2, e2, |, g, g, f, e, |, f2, e2)
    val voice2 = Tune(c, g, c, g, |, a, f, c, g, |, e, e, d, c, |, d, b, c2)

    // PianoPlayer.play(voice1)
    // CelloPlayer.play(voice2)

    Thread.sleep(4000)

    // Terminate the actor system
    system.terminate()
