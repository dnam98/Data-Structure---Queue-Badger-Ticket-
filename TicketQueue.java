//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Ticket Queue
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
 * This class is the queue structure for ticketing.
 * 
 * @author Dongwon
 *
 */
public class TicketQueue implements QueueADT<TicketOrder>, Iterable<TicketOrder> {
  private LinkedOrder front; // a reference to the LinkedOrder at the front of the queue
  private LinkedOrder back; // a reference to the LinkedOrder at the back of the queue
  private int size; // tracking the number of TicketOrders currently in the queue

  /**
   * Creates and returns a new OrderIterator beginning with the current value of front
   * 
   * @return
   */
  public Iterator<TicketOrder> iterator() {
    OrderIterator orderIterator = new OrderIterator(this.front);
    return orderIterator;
  }

  /**
   * Adds a new LinkedOrder containing newElement to the back of the queue, updating the size
   * variable and front/back references appropriately.
   * 
   */
  public void enqueue(TicketOrder newElement) {
    LinkedOrder newLink = new LinkedOrder(newElement);

    // Add to an empty queue
    if (this.front == null) {
      this.front = newLink;
      this.back = newLink;
    }

    // Add to a non-empty queue
    else {
      this.back.setNext(newLink);
      this.back = newLink;
    }

    this.size++;
  }

  /**
   * Removes the next LinkedOrder from the front of the queue and returns its TicketOrder, updating
   * the size variable and front/back references appropriately. Throws a NoSuchElementException if
   * the queue is empty.
   * 
   */
  public TicketOrder dequeue() throws NoSuchElementException {
    // Dequeue from an empty queue
    if (this.front == null) {
      throw new NoSuchElementException("Cannot dequeue from empty queue");
    }

    // Dequeue from a non-empty queue
    LinkedOrder removedLink = this.front;
    LinkedOrder nextLink = this.front.getNext();
    TicketOrder removedOrder = removedLink.getOrder();

    // If queue is size 1
    if (size == 1) {
      this.front = null;
      this.back = null;
    }

    else {
      this.front = nextLink;
    }

    this.size--;
    return removedOrder;
  }

  /**
   * Returns the Order from the LinkedOrder at the front of the queue without removing the
   * LinkedOrder from the queue. Throws a NoSuchElementException if the queue is empty.
   * 
   */
  public TicketOrder peek() throws NoSuchElementException {
    // If the queue is empty
    if (this.front == null) {
      throw new NoSuchElementException("Error: The queue is empty and cannot peek");
    }

    // Queue is not empty
    return this.front.getOrder();
  }

  /**
   * Returns true if and only if the queue is empty.
   * 
   */
  public boolean isEmpty() {
    if (this.front == null) {
      return true;
    }
    return false;
  }

  /**
   * Reverses the given TicketQueue using recursion. This method both returns the reversed queue and
   * alters the original queue in place.
   * 
   * @param queue - the TicketQueue to reverse
   * @return the reversed TicketQueue
   */
  public static TicketQueue reverse(TicketQueue queue) {
    TicketOrder currentOrder;
    TicketQueue reversedQueue = queue;

    // Base case: queue is empty -> then return the empty queue
    if (reversedQueue.front == null) {
      return reversedQueue;
    }
    // Recursive case: queue is not empty
    else {
      currentOrder = reversedQueue.front.getOrder(); // get the order of the front node
      reversedQueue.dequeue(); // pop the front
      reversedQueue = reverse(reversedQueue); 
      reversedQueue.enqueue(currentOrder);
      return reversedQueue;
    }

  }

  /**
   * Creates and returns a String representation of this TicketQueue using an enhanced-for loop. 
   * For example, a queue with three Orders might look like this: 
   * 1001: chris (2) -> 1002: michelle (1) -> 1003: alex (3) -> END
   *
   * @return A String representation of the queue
   */
  @Override
  public String toString() {
    if (this.size == 0)
      return "";
    String qString = "";
    for (TicketOrder o : this) {
      qString += o.toString();
      qString += " -> ";
    }
    qString += "END";
    return qString;
  }

}
