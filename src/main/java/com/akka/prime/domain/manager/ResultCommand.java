package com.akka.prime.domain.manager;

import java.math.BigInteger;

public class ResultCommand implements ManagerCommand {
  public static final long serialVersionUID = 2l;
  private BigInteger prime;

  public ResultCommand(BigInteger prime) {
    this.prime = prime;
  }

  public BigInteger getPrime() {
    return prime;
  }
}
