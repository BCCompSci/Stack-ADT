// file: StringStackC.java
// author: Bob Muller
//
// An implementation of a simple stack ADT.
//
import java.util.*;
import edu.princeton.cs.algs4.StdIn;

public class StringStackC implements StringStack {

  private static final int CAPACITY = 1000;

  // Instance variables.
  //
  private int N;                // The number of Strings in the stack.
  private String[] theStack;

  // A constructor.
  //
  public StringStackC() {
    this.N = 0;
    this.theStack = new String[CAPACITY];
  }

  public void push(String item) {

    if (this.N == CAPACITY)
      throw new RuntimeException("Stack Overflow.");
    else
      this.theStack[this.N++] = item;
  }

  public String pop() {
    if (this.isEmpty())
      throw new NoSuchElementException("Stack Underflow.");
    else {
      String item = this.theStack[--this.N];
      this.theStack[this.N] = null;
      return item;
    }
  }

  public String peek() {
    if (this.isEmpty())
      throw new java.util.NoSuchElementException("Stack Underflow.");
    else
      return this.theStack[this.N - 1];
  }

  public boolean isEmpty() { return this.N == 0; }
  public int size() { return this.N; }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    for(int i = this.N - 1; i >= 0; i--)
      sb.append(this.theStack[i].toString() + "\n");

    return sb.toString();
  }

  public static void main(String[] args) {

    // Unit test. Reads strings from stdin then prints them
    // in reverse order.
    //
    StringStack ss = new StringStackC();

    while(!StdIn.isEmpty())
      ss.push(StdIn.readString());

    while(!ss.isEmpty())
      System.out.format("%s%n", ss.pop());
  }
}
