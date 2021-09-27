package hw;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.collections4.queue.CircularFifoQueue;
import sun.misc.Signal;


// see https://stackoverflow.com/questions/1963806/#21699069
// why we're using this implementation instead of java.util.ArrayQueue!

public class TopWords {

    //public static final int LAST_N_WORDS = 10;
    public static HashMap<String, Integer>
    sortByValue(HashMap<String, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer>> list
                = new LinkedList<>(
                hm.entrySet());

        // Sort the list using lambda expression
        list.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp
                = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public static void main(final String[] args) {

        int howMany = Integer.parseInt(args[0]);
        int minLength = Integer.parseInt(args[1]);
        int lastNWords = Integer.parseInt(args[2]);

        // properly terminate on SIGPIPE received from downstream
        // see also http://lucproglangcourse.github.io/imperative.html#the-role-of-console-applications
        if (System.getProperty("os.name").indexOf("Windows") < 0) {
            Signal.handle(new Signal("PIPE"), (final Signal sig) -> System.exit(1));
        }
        final Iterator<String> input = new Scanner(System.in).useDelimiter("(?U)[^\\p{Alpha}0-9']+");
        final Queue<String> queue = new CircularFifoQueue<>(lastNWords);
        LinkedHashMap<String, Integer> frequencies = new LinkedHashMap<>();

        while (input.hasNext()) {
            final String word = input.next();
            if (word.length() >= minLength) {
                queue.add(word);// the oldest item automatically gets evicted
                frequencies.clear();
                for (String s : queue) {
                    if (!frequencies.containsKey(s)) {
                        frequencies.put(s, 1);
                    } else {
                        int count = frequencies.get(s);
                        frequencies.put(s, count + 1);
                    }
                }
                Map<String, Integer> res = frequencies.entrySet().stream()
                        .limit(howMany)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                System.out.println(sortByValue((HashMap<String, Integer>) res));
            }
        }
    }
}

