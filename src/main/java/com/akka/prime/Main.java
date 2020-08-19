package com.akka.prime;

import akka.actor.typed.ActorSystem;
import com.akka.prime.domain.manager.InstructionCommand;
import com.akka.prime.domain.manager.ManagerCommand;

public class Main {
  public static void main(String[] args) {
    ActorSystem<ManagerCommand> system = ActorSystem.create(ManagerBehaviour.create(), "bigPrime");
    system.tell(new InstructionCommand("start"));
  }
}
