package hw;

import java.util.*;

public class Compare {
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm){
        List<Map.Entry<String, Integer>> list =
                new LinkedList<>(hm.entrySet());
        list.sort(Map.Entry.comparingByValue());

        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
    public static void main(String[] args){
        HashMap<String, Integer> hm = new HashMap<>();

        // enter data into hashmap
        hm.put("Math", 98);
        hm.put("Data Structure", 85);
        hm.put("Database", 91);
        hm.put("Java", 95);
        hm.put("Operating System", 79);
        hm.put("Networking", 80);
        Map<String, Integer> hm1 = sortByValue(hm);

        for (Map.Entry<String, Integer> en : hm1.entrySet()){
            System.out.println("Key = " + en.getKey() + ", Value = " + en.getValue());
        }
    }

}


