package com.akka.basic;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class BasicBehavior extends AbstractBehavior<String> {


  private BasicBehavior(ActorContext<String> context) {
    super(context);
  }

  public static Behavior<String> create(){
    return Behaviors.setup(BasicBehavior::new);
  }

  public Receive<String> createReceive() {

    return newReceiveBuilder()
        .onMessageEquals(
            "hi",
            () -> {
              System.out.println("hi there");
              return this;
            })
        .onMessageEquals(
            "who are you",
            () -> {
              System.out.println("I am " + getContext().getSelf().path());
              return this;
            })
        .onMessageEquals(
            "create child",
            () -> {
              ActorRef<String> secondActor = getContext().spawn(BasicBehavior.create(), "secondActor");
              secondActor.tell("who are you");
              return this;
            })
        .onAnyMessage(
            message -> {
              System.out.println("not supported message!!");
              return this;
            })
        .build();
  }
}
