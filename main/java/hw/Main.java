package hw;

import java.util.*;

import org.apache.commons.collections4.queue.CircularFifoQueue;
import sun.misc.Signal;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.Collections.reverseOrder;

import static java.util.stream.Collectors.*;

// see https://stackoverflow.com/questions/1963806/#21699069
// why we're using this implementation instead of java.util.ArrayQueue!

public class Main {

  //public static final int LAST_N_WORDS = 10;

  public static void main(final String[] args) {

    //int howMany = Integer.parseInt(args[0]);
    //int minLength = Integer.parseInt(args[1]);
    //int lastNWords = Integer.parseInt(args[2]);
    int howMany = 10;
    int minLength = 10;
    int lastNWords = 50;

    // TODO consider using a command-line option library

    // perform argument validity checking
    /*if (args.length > 1) {
      System.err.println("usage: ./target/universal/stage/bin/consoleapp [ last_n_words ]");
      System.exit(2);
    }*/

    //var lastNWords = LAST_N_WORDS;
    /*try {
        lastNWords = Integer.parseInt(args[0]);
        if (lastNWords < 1) {
          throw new NumberFormatException();
        }

    } catch (final NumberFormatException ex) {
      System.err.println("argument should be a natural number!");
      System.exit(4);
    }*/

    // properly terminate on SIGPIPE received from downstream
    // see also http://lucproglangcourse.github.io/imperative.html#the-role-of-console-applications
    if (System.getProperty("os.name").indexOf("Windows") < 0) {
      Signal.handle(new Signal("PIPE"), (final Signal sig) -> System.exit(1));
    }
    final Iterator<String> input = new Scanner(System.in).useDelimiter("(?U)[^\\p{Alpha}0-9']+");
    final Queue<String> queue = new CircularFifoQueue<>(lastNWords);
    Map<String, Integer> frequencies = new HashMap<>();
    List<String> keys = new ArrayList<>(frequencies.keySet());



    List<Map.Entry<String, Integer>> sorted =
            frequencies.entrySet()
                    .stream()
                    .sorted(reverseOrder(Map.Entry.comparingByKey()))
                    .collect(Collectors.toList());


    int count = 0;
    while (input.hasNext()) {
      final String word = input.next();
      if (word.length() >= minLength) {
        queue.add(word); // the oldest item automatically gets evicted
        for (String s : queue) {
          count++;
          frequencies.put(s, count);
        }
      }
      /*for (Object key : keys) {
        int obj = (int) key;
        for (Map.Entry<String, Integer> entry : frequencies.entrySet()) {
          if (obj <= howMany) {
            System.out.println(sorted);
          }
        }
      }*/
    }
  }
}
