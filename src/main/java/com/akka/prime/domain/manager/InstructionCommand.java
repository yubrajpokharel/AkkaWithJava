package com.akka.prime.domain.manager;

public class InstructionCommand implements ManagerCommand {
  public static final long serialVersionUID = 2l;
  private String message;

  public InstructionCommand(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
