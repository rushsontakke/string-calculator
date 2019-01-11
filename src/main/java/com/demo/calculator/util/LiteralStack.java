package com.demo.calculator.util;

import java.util.List;
import java.util.Stack;

public class LiteralStack
{
  private List<Literals> literals;

  public LiteralStack() {
    literals = new Stack<Literals>();
  }

  public boolean isEmpty() {
    return literals.size() == 0;
  }
  public Literals top() {
    return literals.get(literals.size()-1);
  }

  public void push(Literals t) {
    literals.add(t);
  }
  public void pop() {
    literals.remove(literals.size()-1);
  }
}

