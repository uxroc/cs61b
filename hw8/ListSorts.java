/* ListSorts.java */

import list.*;

public class ListSorts {

  private final static int SORTSIZE = 10000000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
    // Replace the following line with your solution.
    LinkedQueue ret = new LinkedQueue();
    try{
    while(!q.isEmpty()){
	    LinkedQueue nq = new LinkedQueue();
	    nq.enqueue(q.dequeue());
	    ret.enqueue(nq);
    }
    }catch(QueueEmptyException e){
    }
    return ret;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
    // Replace the following line with your solution.
    LinkedQueue ret = new LinkedQueue();
    try{
    while(!q1.isEmpty()&&!q2.isEmpty()){
	    while(!q1.isEmpty()&&((Comparable)q1.front()).compareTo((Comparable)q2.front())<=0) ret.enqueue(q1.dequeue());
	    if(q1.isEmpty()) break;
	    while(!q2.isEmpty()&&((Comparable)q2.front()).compareTo((Comparable)q1.front())<=0) ret.enqueue(q2.dequeue());
    }
    while(!q1.isEmpty()){
	    ret.enqueue(q1.dequeue());
    }
    while(!q2.isEmpty()){
	    ret.enqueue(q2.dequeue());
    }	    
    }catch(QueueEmptyException e){
    }
    return ret;
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
    // Your solution here.
    try{
    while(!qIn.isEmpty()){
	    if(((Comparable)qIn.front()).compareTo(pivot)<0) qSmall.enqueue(qIn.dequeue());
	    else if(((Comparable)qIn.front()).compareTo(pivot)==0) qEquals.enqueue(qIn.dequeue());
	    else qLarge.enqueue(qIn.dequeue());
    }
    }catch(QueueEmptyException e){
    }

  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
    // Your solution here.
    LinkedQueue qq = makeQueueOfQueues(q);
    if(qq.isEmpty()) return;
    try{
    while(qq.size()>1){
	    LinkedQueue q1 = (LinkedQueue)qq.dequeue(), q2 = (LinkedQueue)qq.dequeue();
	    qq.enqueue(mergeSortedQueues(q1,q2));
    }
    q.append((LinkedQueue)qq.dequeue());
    }catch(QueueEmptyException e){
    }
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
    // Your solution here.
    Comparable pivot = (Comparable)q.nth(((int)Math.random())%q.size()+1);
    LinkedQueue qSmall = new LinkedQueue();
    LinkedQueue qLarge = new LinkedQueue();
    LinkedQueue qEquals = new LinkedQueue();
    partition(q, pivot, qSmall, qEquals, qLarge);
    if(qSmall.size()>1) quickSort(qSmall);
    if(qLarge.size()>1) quickSort(qLarge);
    q.append(qSmall);
    q.append(qEquals);
    q.append(qLarge);
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/
  public static void main(String [] args) {

    LinkedQueue q = makeRandom(10);
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString());

    q = makeRandom(10);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    Timer stopWatch = new Timer();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");
  }

}
