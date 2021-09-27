package hw;

import java.util.Iterator;
import java.util.Queue;
import org.apache.commons.collections4.queue.CircularFifoQueue;

class SlidingQueue {

  protected final Queue<String> queue;

  public SlidingQueue(final int queueSize) {
    this.queue = new CircularFifoQueue<>(queueSize);
  }

  public void process(final Iterator<String> input, final OutputObserver output) {
    while (input.hasNext()) {
      final String word = input.next();
      queue.add(word); // the oldest item automatically gets evicted
      output.accept(queue);
    }
  }
}
