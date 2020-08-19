package com.akka.prime;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import com.akka.prime.domain.manager.InstructionCommand;
import com.akka.prime.domain.manager.ManagerCommand;
import com.akka.prime.domain.manager.ResultCommand;
import com.akka.prime.domain.worker.WorkerCommand;
import java.math.BigInteger;
import java.util.SortedSet;
import java.util.TreeSet;

public class ManagerBehaviour extends AbstractBehavior<ManagerCommand> {

  private SortedSet<BigInteger> sortedSet = new TreeSet<>();

  private ManagerBehaviour(ActorContext<ManagerCommand> context) {
    super(context);
  }

  public static Behavior<ManagerCommand> create() {
    return Behaviors.setup(ManagerBehaviour::new);
  }

  @Override
  public Receive<ManagerCommand> createReceive() {
    return newReceiveBuilder()
        .onMessage(
            InstructionCommand.class,
            command -> {
              if (command.getMessage().equalsIgnoreCase("start")) {
                for (int i = 0; i < 20; i++) {
                  ActorRef<WorkerCommand> worker =
                      getContext().spawn(WorkerBehaviour.create(), "workerActor-" + i);
                  worker.tell(new WorkerCommand("start", getContext().getSelf()));
                }
              }
              return this;
            })
        .onMessage(
            ResultCommand.class,
            command -> {
              sortedSet.add(command.getPrime());
              System.out.println("I received message ");
              if(sortedSet.size() == 20){
                sortedSet.forEach(System.out::println);
              }
              return this;
            })
        .build();
  }
}
