package com.akka.prime;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import com.akka.prime.domain.manager.ResultCommand;
import com.akka.prime.domain.worker.WorkerCommand;
import java.math.BigInteger;
import java.util.Random;

public class WorkerBehaviour extends AbstractBehavior<WorkerCommand> {

  private WorkerBehaviour(ActorContext<WorkerCommand> context) {
    super(context);
  }

  public static Behavior<WorkerCommand> create() {
    return Behaviors.setup(WorkerBehaviour::new);
  }

  @Override
  public Receive<WorkerCommand> createReceive() {
    return newReceiveBuilder()
        .onAnyMessage(
            command -> {
              if (command.getMessage().equalsIgnoreCase("start")) {
                BigInteger bigInteger = new BigInteger(200, new Random());
                command.getSender().tell(new ResultCommand(bigInteger.nextProbablePrime()));
              }
              return this;
            })
        .build();
  }
}
