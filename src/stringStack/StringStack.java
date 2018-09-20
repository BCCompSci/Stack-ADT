// file: StringStack.java
// author: Bob Muller
//
// An API for simple stacks of Strings.
//
public interface StringStack {

  void push(String s);
  String pop();
  String peek();
  boolean isEmpty();
  int size();
  String toString();
}
