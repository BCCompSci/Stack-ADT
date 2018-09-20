// file: StringStackC.java
// author: Bob Muller
//
// An implementation of a simple stack ADT.
//
import java.util.*;
import edu.princeton.cs.algs4.StdIn;

@SuppressWarnings("unchecked")

public class ResizingArrayStack<T> implements Stack<T> {

  private static final int CAPACITY = 2;

  // Instance variables.
  //
  private int N;                // The number of Strings in the stack.
  private T[] stack;

  // A constructor.
  //
  public ResizingArrayStack() {
    this.N = 0;
    this.stack = (T[]) new Object[CAPACITY];
  }

  private void resize(int n) {
    T[] temp = (T[]) new Object[n];
    for(int i = 0; i < this.N; i++)
      temp[i] = this.stack[i];
    this.stack = temp;
  }

  public void push(T item) {
    if (this.N == this.stack.length) resize(this.N * 2);
    this.stack[this.N++] = item;
  }

  public T pop() {
    if (this.isEmpty())
      throw new NoSuchElementException("Stack Underflow.");
    else {
      T item = this.stack[--this.N];
      this.stack[this.N] = null;
      if(this.N < this.stack.length / 4)
        resize(this.stack.length / 2);
      return item;
    }
  }

  public T peek() {
    if (this.isEmpty())
      throw new java.util.NoSuchElementException("Stack Underflow.");
    else
      return this.stack[this.N - 1];
  }

  public boolean isEmpty() { return this.N == 0; }
  public int size() { return this.N; }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for(int i = this.N - 1; i >= 0; i--)
      sb.append(this.stack[i].toString() + "\n");
    return sb.toString();
  }

  public static void main(String[] args) {

    // Unit test. Reads strings from stdin then prints them
    // in reverse order.
    //
    Stack<String> ss = new ResizingArrayStack<String>();
    Stack<Integer> is = new ResizingArrayStack<Integer>();

    is.push(343);

    while(!StdIn.isEmpty())
      ss.push(StdIn.readString());

    while(!ss.isEmpty())
      System.out.println(ss.pop());
  }
}
