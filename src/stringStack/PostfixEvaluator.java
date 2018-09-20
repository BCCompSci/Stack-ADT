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
// 2 2 3 ^ ^ctrl-d
//
import edu.princeton.cs.algs4.StdIn;

public class PostfixEvaluator {

  private static boolean isNumber(String token) {
    return (!token.equals("+") && !token.equals("*") &&
            !token.equals("-") &&!token.equals("/") &&
            !token.equals("^"));
  }

  public static void main(String[] args) {

    StringStack myStack = new StringStackC();

    System.out.format("Enter a postfix expression followed by ctrl-d:");

    while(!StdIn.isEmpty()) {

      String token = StdIn.readString();

      if(PostfixEvaluator.isNumber(token))
        myStack.push(token);
      else {
        double
          operand1 = Double.parseDouble(myStack.pop()),
          operand2 = Double.parseDouble(myStack.pop()),
          result = 0.0;
        if(token.equals("+"))       result = operand1 + operand2;
        else if (token.equals("*")) result = operand1 * operand2;
        else if (token.equals("^")) result = Math.pow(operand2, operand1);
        else if (token.equals("-")) result = operand2 - operand1;
        else if (token.equals("/")) result = operand2 / operand1;

        myStack.push(Double.toString(result));
      }
    }
    System.out.format("The value is %s%n", myStack.pop());       
  }
}
