package com.akka.basic;

import akka.actor.typed.ActorSystem;

public class Main {

  public static void main(String[] args) {
    ActorSystem<String> actorSystem = ActorSystem.create(BasicBehavior.create(), "firstActorSystem");
    actorSystem.tell("hi");
    actorSystem.tell("who are you");
    actorSystem.tell("create child");
    actorSystem.tell("dodododo");
  }
}
