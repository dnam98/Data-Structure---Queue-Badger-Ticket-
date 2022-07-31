//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Iterator
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
 * This class is the iterator for the TicketQueue
 * 
 * @author Dongwon
 *
 */
public class OrderIterator implements Iterator<TicketOrder> {
  private LinkedOrder current; // the LinkedOrder that it's currently using

  /**
   * Constructor, initializes current to the provided starting LinkedOrder. Does not care whether
   * the argument value is null
   * 
   * @param start - the starting node
   */
  public OrderIterator(LinkedOrder start) {
    this.current = start;
  }

  /**
   * Returns true if and only if the iteration has more ticket orders
   * 
   */
  public boolean hasNext() {
    if (current != null) {
      return true;
    }

    else {
      return false;
    }
  }

  /**
   * Throws a NoSuchElementException with a descriptive error message if the iteration does not
   * have more ticket orders to return. Otherwise returns the next TicketOrder and updates the
   * current field appropriately.
   * 
   */
  public TicketOrder next() throws NoSuchElementException {
    TicketOrder returnOrder = current.getOrder();

    if (!hasNext()) {
      throw new NoSuchElementException("Error: No more ticket orders to return");
    }

    current = current.getNext();
    return returnOrder;
  }
}
