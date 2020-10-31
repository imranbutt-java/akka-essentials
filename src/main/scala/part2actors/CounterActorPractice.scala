package part2actors

import akka.actor.{Actor, ActorSystem, Props}

/* imransarwar created on 30/10/2020*/
object CounterActorPractice extends App {
  object Counter {
    case object Initialize
    case object Increment
    case object Decrement
    case object Square
    case object Status
  }

  class Counter extends Actor {
    import Counter._
    override def receive: Receive = {
      case Initialize => context.become(counter(0))
    }

    def counter(count: Int): Receive = {
      case Increment => context.become(counter(count + 1))
      case Decrement => context.become(counter(count - 1))
      case Square => context.become(counter(count * 2))
      case Status => {
        println(s"Current Count: $count")
      }
    }
  }

  //Test
  import Counter._
  val system = ActorSystem("mainSystem")
  val counter = system.actorOf(Props[Counter], "counter")
  counter ! Initialize
  counter ! Increment
  counter ! Status
  counter ! Increment
  counter ! Status
  counter ! Square
  counter ! Increment
  counter ! Status

}
