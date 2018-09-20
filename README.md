# CSCI 1102 Computer Science 2

### Spring 2018

------

## Lecture Notes STILL DRAFT

### Week 3: The Stack ADT

#### Topics:

1. Odds & Ends
2. The StringStack ADT
3. A Postfix Expression Evaluator
---

## 1. Odds and Ends

1. Wrapper classes & autoboxing
2. Notes on Memory Management

## 2. The StringStack ADT



We'll proceed through several iterations, attempting to illustrate important points relating to representation and genericity. We assume known the basic Last-In-First-Out (LIFO) behavior of stacks.

  The specification:

```java
public interface StringStack {

  void push(String s);
  String pop();
  String peek();
  boolean isEmpty();
  int size();
  String toString();
}
```

An implementation:

```java
// A fixed size stack restricted to Strings.
//
public class StringStackC implements StringStack {

  private final static int CAPACITY = 1000;

  // Instance variables.
  //
  private String[] theStack;
  private int N;

  public StringStackC() {
    this.theStack = new String[CAPACITY];
    this.N = 0;
  }

  public void push(String item) {
    if (this.N == CAPACITY)
      throw new RuntimeException("Stack Overflow.");
    else
      this.theStack[this.N++] = item;
  }

  public String pop() {
    if (this.isEmpty())
      throw new RuntimeException("Stack Underflow.");
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

  public int size() { return this.N; }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    for(int i = this.N - 1; i >= 0; i--)
          sb.append(this.theStack[i].toString() + "\n");

    return sb.toString();
  }
}
```

## 3. A Postfix Arithmetic Expression Evaluation

In this section we develop a simple expression evaluator application. This is application is a *client* of the StringStack ADT. 

Simple algebraic expressions are formed from numbers, constant symbols (e.g., $a$, $b$, $c$) and variables (e.g., $x$, $y$, $z$) combined with the basic operations: addition, subtraction, multiplication and division. For addition, subtraction and division, explicit symbolic operators appear between the operands. Multiplication is sometimes denoted implicitly by juxtaposition as in $3x$ and sometimes with an explicit dot operator as in $3\cdot x$. 

Of course each operator has its own properties and evaluation priorities or precedence. Multiplication and division have higher precedence than addition or subtraction. In $3x + 5$ (and in $3\cdot x + 5$) it is understood that the multiplication $3\cdot x$ is done first. The parentheses $(\ldots)$ were introduced in the 17th century to allow a writer to group subexpressions. So one could write $3\cdot (x + 5)$. 

There are alternative notations which don't require parentheses. For $3\cdot x + 5$ one might write $+\ \cdot\ 3\ x\ 5$ or $3\ x\ \cdot\ 5\ +$. For $3 \cdot (x + 5)$ we would have $\cdot\ 3\ +\ x\ 5$ or 

```java
// file: PostfixEvaluator.java
// author: Bob Muller
//
// This file contains a simple client of the StringStack ADT. It
// implements an evaluator of postfix expressions involving floating 
// point numbers under:
//   addition
//   subtraction
//   multiplication
//   division
//   exponentiation.
//
// To run it, provide postfix input with spaces followed by ctrl-d:
// 
// 2 2 3 ^ ^ ctrl-d
//
import edu.princeton.cs.algs4.StdIn;

public class PostfixEvaluator {

  private static boolean isNumber(String token) {
    return (!token.equals("+") && !token.equals("*") &&
            !token.equals("-") &&!token.equals("/") && 
            !token.equals("^"));
  }
  
  public static void main(String[] args) {
    
    StringStack operandStack = new StringStackC();

    System.out.format("Enter a postfix expression followed by ctrl-d:");
    
    while(!StdIn.isEmpty()) {
      
      String token = StdIn.readString();
      
      if(PostfixEvaluator.isNumber(token))
        operandStack.push(token);
      else {
        double 
          operand1 = Double.parseDouble(operandStack.pop()),
          operand2 = Double.parseDouble(operandStack.pop()),
          result = 0.0;
        if(token.equals("+"))       result = operand1 + operand2;
        else if (token.equals("*")) result = operand1 * operand2;
        else if (token.equals("^")) result = Math.pow(operand2, operand1);
        else if (token.equals("-")) result = operand2 - operand1;
        else if (token.equals("/")) result = operand2 / operand1;
        
        operandStack.push(Double.toString(result));
      }
    } 
    System.out.format("The value is %s%n", operandStack.pop());       
  }
}
```

