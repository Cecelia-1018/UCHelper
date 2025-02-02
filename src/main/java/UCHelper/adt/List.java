package UCHelper.adt;

import java.io.Serializable;
import java.util.Iterator;

/**
 * List.java is an ADT that operate like a java.util.List which acted as a fundamental list of an object to store values.
 * @author Dennis
 * @version 1.0
 */
public class List<T> implements ListInterface<T>, Serializable {

  private Node firstNode; // reference to first node
  private int length;  	// number of entries in list

  public List() {
    clear();
  }

  @Override
  public final void clear() {
    firstNode = null;
    length = 0;
  }

  @Override
  public boolean add(T element) {
    Node newNode = new Node(element);	// create the new node

    if (isEmpty()) {
      firstNode = newNode;
    } else {                        // add to end of nonempty list
      Node currentNode = firstNode;	// traverse linked list with p pointing to the current node
      while (currentNode.next != null) { // while have not reached the last node
        currentNode = currentNode.next;
      }
      currentNode.next = newNode; // make last node reference new node
    }

    length++;
    return true;
  }

  @Override
  public boolean add(int newPosition, T element) { // OutOfMemoryError possible
    boolean isSuccessful = true;

    if ((newPosition >= 1) && (newPosition <= length + 1)) {
      Node newNode = new Node(element);

      if (isEmpty() || (newPosition == 1)) { // case 1: add to beginning of list
        newNode.next = firstNode;
        firstNode = newNode;
      } else {								// case 2: list is not empty and newPosition > 1
        Node nodeBefore = firstNode;
        for (int i = 1; i < newPosition - 1; ++i) {
          nodeBefore = nodeBefore.next;		// advance nodeBefore to its next node
        }

        newNode.next = nodeBefore.next;	// make new node point to current node at newPosition
        nodeBefore.next = newNode;		// make the node before point to the new node
      }

      length++;
    } else {
      isSuccessful = false;
    }

    return isSuccessful;
  }

  @Override
  public boolean remove(T element) {
    T result = null;                 // return value

    if ((length >= 1) && (length <= length)) {
      if (length == 1) {      // case 1: remove first entry
        result = firstNode.data;     // save entry to be removed
        firstNode = firstNode.next;
      } else {                         // case 2: givenPosition > 1
        Node nodeBefore = firstNode;
        for (int i = 1; i < length - 1; ++i) {
          nodeBefore = nodeBefore.next;		// advance nodeBefore to its next node
        }
        result = nodeBefore.next.data;  // save entry to be removed
        nodeBefore.next = nodeBefore.next.next;	// make node before point to node after the
      } 		
      length--;
    }

    return true; // return removed entry, or null if operation fails
  }

  @Override
  public boolean replace(int givenPosition, T element) {
    boolean isSuccessful = true;

    if ((givenPosition >= 1) && (givenPosition <= length)) {
      Node currentNode = firstNode;
      for (int i = 0; i < givenPosition - 1; ++i) {
        currentNode = currentNode.next;		// advance currentNode to next node
      }
      currentNode.data = element;	// currentNode is pointing to the node at givenPosition
    } else {
      isSuccessful = false;
    }

    return isSuccessful;
  }

  @Override
  public T getEntry(int givenPosition) {
    T result = null;

    if ((givenPosition >= 1) && (givenPosition <= length)) {
      Node currentNode = firstNode;
      for (int i = 0; i < givenPosition - 1; ++i) {
        currentNode = currentNode.next;		// advance currentNode to next node
      }
      result = currentNode.data;	// currentNode is pointing to the node at givenPosition
    }

    return result;
  }

  @Override
  public boolean contains(T element) {
    boolean found = false;
    Node currentNode = firstNode;

    while (!found && (currentNode != null)) {
      if (element.equals(currentNode.data)) {
        found = true;
      } else {
        currentNode = currentNode.next;
      }
    }
    return found;
  }

  @Override
  public int getLength() {
    return length;
  }

  @Override
  public boolean isEmpty() {
    boolean result;

    result = length == 0;

    return result;
  }

  @Override
  public boolean isFull() {
    return false;
  }

  @Override
  public String toString() {
    String outputStr = "";
    Node currentNode = firstNode;
    while (currentNode != null) {
      outputStr += currentNode.data + "\n";
      currentNode = currentNode.next;
    }
    return outputStr;
  }

    @Override
    public void forEach(T element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<T> getIterator() {
        return new SetIterator();
    }
    
    private class SetIterator implements Iterator<T> {
        private int iteratorIndex =0;
        private T data;
        private T next;
        
        
        @Override
        public boolean hasNext() {
            return iteratorIndex < length;
        }

        @Override
        public T next() {
            if (hasNext())
                return next;
            else
                return null;
        }
    }
    
    private class Node {

        private T data;
        private Node next;

        private Node(T data) {
          this.data = data;
          this.next = null;
        }

        private Node(T data, Node next) {
          this.data = data;
          this.next = next;
        }
    }

}
