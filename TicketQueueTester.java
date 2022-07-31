//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Ticket Queue Tester
// Course: CS 300 Summer 2021
//
// Author: Dongwon Nam
// Email: dnam9@wisc.edu
// Lecturer: Chris Magnano
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class tests the Queue project
 * 
 * @author Dongwon
 *
 */
public class TicketQueueTester {

  /**
   * Tester method for OrderIterator
   * 
   * @return true if passed without errors
   */
  public static boolean testOrderIterator() {
    // Reset ID
    TicketOrder.resetIDGenerator();

    // Construct
    TicketOrder testOrder1 = new TicketOrder("Jack", 2);
    TicketOrder testOrder2 = new TicketOrder("Bob", 3);
    LinkedOrder testNode1 = new LinkedOrder(testOrder1);
    LinkedOrder testNode2 = new LinkedOrder(testOrder2);
    OrderIterator testIterator = new OrderIterator(testNode1);

    // Link two nodes
    testNode1.setNext(testNode2);

    // hasNext() test
    boolean expected = true;
    boolean actual = testIterator.hasNext();

    if (expected != actual) {
      System.out.println("testOrderIterator() failed: Error in hasNext() method.");
      System.out.println("Expected: " + expected + " Acutal: " + actual);
      return false;
    }

    TicketOrder nextNode = testIterator.next();

    // next() test
    String expectedName = "Jack";
    int expectedTicketNum = 2;
    String actualName = nextNode.getStudentName();
    int actualTicketNum = nextNode.getNumTickets();

    if (!expectedName.equals(actualName)) {
      System.out.println("testOrderIterator() failed: Error in next() method.");
      System.out.println("Expected: " + expectedName + " Acutal: " + actualName);
      return false;
    }

    if (expectedTicketNum != actualTicketNum) {
      System.out.println("testOrderIterator() failed: Error in next() method.");
      System.out.println("Expected: " + expectedTicketNum + " Acutal: " + actualTicketNum);
      return false;
    }

    return true;
  }

  /**
   * Tester method for enqueue() method in TicketQueue class
   * 
   * @return true if passed
   */
  public static boolean testTicketQueueEnqueue() {
    // Reset ID
    TicketOrder.resetIDGenerator();

    TicketQueue q = new TicketQueue();
    String actualQueue;
    String expectedQueue;

    // Enqueue to an empty queue
    q.enqueue(new TicketOrder("Student 1", 2));

    // Print the queue
    actualQueue = q.toString();
    expectedQueue = "1001: Student 1 (2) -> END";

    if (!actualQueue.equals(expectedQueue)) {
      System.out.println("Error in TicketQueueEnqueue: Enqueue to an empty queue");
      System.out.println("Expected: " + expectedQueue + " Actual: " + actualQueue);
      return false;
    }

    // Enqueue another LinkedOrder
    q.enqueue(new TicketOrder("Student 2", 2));

    // Print the queue
    actualQueue = q.toString();
    expectedQueue = "1001: Student 1 (2) -> 1002: Student 2 (2) -> END";

    if (!actualQueue.equals(expectedQueue)) {
      System.out.println("Error in TicketQueueEnqueue: Enqueue to a non-empty queue");
      System.out.println("Expected: " + expectedQueue + " Actual: " + actualQueue);
      return false;
    }

    return true;
  }

  /**
   * Tester method for dequeue() method in TicketQueue class
   * 
   * @return true if test passed
   */
  public static boolean testTicketQueueDequeue() {
    // Reset ID
    TicketOrder.resetIDGenerator();

    String actualQueue;
    String expectedQueue;
    String expectedOrder;
    String actualOrder;
    TicketQueue q = new TicketQueue();

    // Enqueue to an empty queue
    q.enqueue(new TicketOrder("Student 1", 2));
    q.enqueue(new TicketOrder("Student 2", 2));
    q.enqueue(new TicketOrder("Student 3", 2));

    // The queue is 1001: Student 1 (2) -> 1002: Student 2 (2) -> 1003: Student 3 (2) -> END

    // Dequeue once
    expectedOrder = "1001: Student 1 (2)";
    actualOrder = q.dequeue().toString();

    // Catch NullPointerException if dequeue returned null
    try {
      // Check the removed order
      if (!expectedOrder.equals(actualOrder)) {
        System.out.println("Error in TicketQueueDequeue: Dequeue on a 3 sized queue.");
        System.out.println("Expected order: " + expectedOrder + " Actual order: " + actualOrder);
        return false;
      }
    } catch (NullPointerException e) {
      System.out.println("Error in TicketQueueDequeue: Returned null instead of order");
    }

    // Check the remaining queue
    expectedQueue = "1002: Student 2 (2) -> 1003: Student 3 (2) -> END";
    actualQueue = q.toString();
    try {
      if (!expectedQueue.equals(actualQueue)) {
        System.out.println("Error in TicketQueueDequeue: Dequeue on a 3 sized queue.");
        System.out.println("Expected queue: " + expectedQueue + " Actual queue: " + actualQueue);
        return false;
      }
    } catch (NullPointerException e) {
      System.out.println("Error in TicketQueueDequeue: Returned null instead of order");
    }

    // The queue is 1002: Student 2 (2) -> 1003: Student 3 (2) -> END
    // Dequeue again
    expectedOrder = "1002: Student 2 (2)";
    actualOrder = q.dequeue().toString();

    // Check the removed order
    try {
      if (!expectedOrder.equals(actualOrder)) {
        System.out
            .println("Error in TicketQueueDequeue: " + "Dequeue on a non-empty queue (size > 1).");
        System.out.println("Expected order: " + expectedOrder + " Actual order: " + actualOrder);
        return false;
      }
    } catch (NullPointerException e) {
      System.out.println("Error in TicketQueueDequeue: Returned null instead of order");
    }

    // Check the remaining queue
    expectedQueue = "1003: Student 3 (2) -> END";
    actualQueue = q.toString();
    try {
      if (!expectedQueue.equals(actualQueue)) {
        System.out
            .println("Error in TicketQueueDequeue: " + "Dequeue on a non-empty queue (size > 1).");
        System.out.println("Expected queue: " + expectedOrder + " Actual queue: " + actualOrder);
        return false;
      }
    } catch (NullPointerException e) {
      System.out.println("Error in TicketQueueDequeue: Returned null instead of order");
    }

    // The queue is 1003: Student 3 (2) -> END

    // Dequeue on a single element
    expectedOrder = "1003: Student 3 (2)";
    actualOrder = q.dequeue().toString();

    try {
      // Check the removed order
      if (!expectedOrder.equals(actualOrder)) {
        System.out.println("Error in TicketQueueDequeue: Dequeue on a single element queue.");
        System.out.println("Expected order: " + expectedOrder + " Actual order: " + actualOrder);
        return false;
      }
    } catch (NullPointerException e) {
      System.out.println("Error in TicketQueueDequeue: Returned null instead of order");
    }

    // Check the remaining queue, which is empty
    expectedQueue = "";
    actualQueue = q.toString();
    try {
      if (!expectedQueue.equals(actualQueue)) {
        System.out.println("Error in TicketQueueDequeue: Dequeue on a single element queue.");
        System.out.println("Expected queue: " + expectedOrder + " Actual queue: " + actualOrder);
        return false;
      }
    } catch (NullPointerException e) {
      System.out.println("Error in TicketQueueDequeue: Returned null instead of order");
    }

    // Dequeue on an empty queue
    try {
      q.dequeue();
    } catch (NoSuchElementException e) {
      e.getMessage();
    }

    return true;
  }

  /**
   * Tester method for peek() method in TicketQueue class
   * 
   * @return true if test passed
   */
  public static boolean testTicketQueuePeek() {
    // Reset ID
    TicketOrder.resetIDGenerator();

    TicketQueue q = new TicketQueue();
    String expectedOrder;
    String actualOrder;

    // Peek on empty queue
    try {
      q.peek();
    } catch (NoSuchElementException e) {
      e.getMessage();
    }

    // Enqueue to an empty queue
    q.enqueue(new TicketOrder("Student 1", 2));
    q.enqueue(new TicketOrder("Student 2", 2));
    q.enqueue(new TicketOrder("Student 3", 2));

    // The queue is 1001: Student 1 (2) -> 1002: Student 2 (2) -> 1003: Student 3 (2) -> END

    // Peek on the first queue order
    actualOrder = q.peek().toString();
    expectedOrder = "1001: Student 1 (2)";

    if (!actualOrder.equals(expectedOrder)) {
      System.out.println("Error in TicketQueuePeek");
      System.out.println("Expected: " + expectedOrder + " Actual: " + actualOrder);
      return false;
    }

    return true;
  }

  /**
   * Tester method for isEmpty() method in TicketQueue class
   * 
   * @return true if test passed
   */
  public static boolean testTicketQueueIsEmpty() {
    // Reset ID
    TicketOrder.resetIDGenerator();

    TicketQueue q = new TicketQueue();
    boolean expected = true;
    boolean actual = q.isEmpty();

    if (expected != actual) {
      System.out.println("Error in TicketQueueIsEmpty");
      System.out.println("Expected: " + expected + " Actual: " + actual);
      return false;
    }

    return true;
  }

  /**
   * Tester method for reverse() method in TicketQueue class
   * 
   * @return true if test passed
   */
  public static boolean testTicketQueueReverse() {
    TicketOrder.resetIDGenerator(); // Reset ID

    TicketQueue q = new TicketQueue();
    q.enqueue(new TicketOrder("student1", 2));
    q.enqueue(new TicketOrder("student2", 2));
    q.enqueue(new TicketOrder("student3", 2));
    q.enqueue(new TicketOrder("student4", 2));

    // Queue is now
    // 1001: student1 (2) -> 1002: student2 (2) -> 1003: student3 (2) -> 1004: student4 (2) -> END

    // Reverse the queue
    TicketQueue.reverse(q);

    String expected =
        "1004: student4 (2) -> 1003: student3 (2) -> 1002: student2 (2) -> 1001: student1 (2)"
            + " -> END";
    String actual = q.toString();

    if (!expected.equals(actual)) {
      System.out.println("Error in TicketQueueReverse");
      System.out.println("Expected: " + expected + " Actual: " + actual);
      return false;
    }

    return true;
  }

  /**
   * This method runs all the tester methods
   * 
   * @return true if and only if all test methods succeed; false otherwise
   */
  public static boolean runAllTests() {
    if (!testOrderIterator()) {
      System.out.println("Error in testOrderIterator");
      return false;
    }

    if (!testTicketQueueEnqueue()) {
      System.out.println("Error in testTicketQueueEnqueue");
      return false;
    }

    if (!testTicketQueueDequeue()) {
      System.out.println("Error in testTicketQueueDequeue");
      return false;
    }

    if (!testTicketQueuePeek()) {
      System.out.println("Error in testTicketQueuePeek");
      return false;
    }

    if (!testTicketQueueIsEmpty()) {
      System.out.println("Error in testTicketQueueIsEmpty");
      return false;
    }

    if (!testTicketQueueReverse()) {
      System.out.println("Error in testTicketQueueReverse");
      return false;
    }

    System.out.println("All tests have passed");
    return true;
  }

  /**
   * The provided demo code
   * 
   */
  public static void demo() {
    TicketQueue q = new TicketQueue();
    q.enqueue(new TicketOrder("student1", 2));
    System.out.println(q.toString());
    q.enqueue(new TicketOrder("student2", 2));
    q.enqueue(new TicketOrder("student3", 2));
    q.enqueue(new TicketOrder("student4", 2));
    System.out.println(q.toString());
    q.enqueue(new TicketOrder("student5", 2));
    q.enqueue(new TicketOrder("student6", 2));
    q.enqueue(new TicketOrder("student7", 2));
    System.out.println(q.toString());

    System.out.println(q.peek()); // Should peek at 1
    System.out.println(q.dequeue()); // Should dequeue 1
    System.out.println(q.toString());
    System.out.println(q.dequeue());
    System.out.println(q.dequeue());
    System.out.println(q.dequeue());
    System.out.println(q.dequeue());
    System.out.println(q.dequeue());
    System.out.println(q.dequeue());
    try {
      q.dequeue(); // Should throw exception
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
    System.out.println(q.toString()); // should be empty string
    q.enqueue(new TicketOrder("student8", 2));
    q.enqueue(new TicketOrder("student9", 2));
    q.enqueue(new TicketOrder("student10", 2));
    q.enqueue(new TicketOrder("student11", 2));
    q.enqueue(new TicketOrder("student12", 2));
    q.enqueue(new TicketOrder("student13", 2));

    System.out.println("Printing from iterator:");
    Iterator<TicketOrder> iter = q.iterator();
    while (iter.hasNext()) {
      System.out.println(iter.next());
    }

    System.out.println("Reversing:");
    System.out.println(q.toString());
    TicketQueue.reverse(q);
    System.out.println(q.toString());
    System.out.println(q.toString());
    q.dequeue();
    System.out.println(q.toString());
    q.dequeue();
    System.out.println(q.toString());
    q.dequeue();
    System.out.println("Reversing Back:");
    TicketQueue.reverse(q);
    System.out.println(q.toString());
    q.dequeue();
    System.out.println(q.toString());
    q.dequeue();
    System.out.println(q.toString());
    q.dequeue();
    System.out.println(q.toString()); // Should print empty string
  }


  /**
   * Main method where calls the tester methods
   * 
   * @param args
   */
  public static void main(String[] args) {

    // testOrderIterator();
    // testTicketQueueEnqueue();
    // testTicketQueueDequeue();
    // testTicketQueuePeek();
    // testTicketQueueIsEmpty();
    // testTicketQueueReverse();
    // demo();
    // runAllTests();

  }

}
