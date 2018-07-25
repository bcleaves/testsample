import java.util.*;

public class Sing {

    public static Sing instance = new Sing();

    public static int value = 777;

    public void Sing() {
        System.out.println("got here");
    }

    public static Sing instance() {
        return instance;
    }

    private void iterate(ArrayList<String> l) {


        // ArrayList
        for(String s : l) {
            System.out.println("string: " + s);

        }

        // HashMap
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        Iterator iter = map.entrySet().iterator();
        while(iter.hasNext()) {
           iter.next();
        }

        // Vector
        Vector<String> v = new Vector<String> ();
        for (String s : v) {

        }

        // Set
        HashSet<String> hs = new HashSet<String>();
        for(String s : hs) {

        }

        // TreeMap
        TreeMap<String, Integer> tp = new TreeMap<String, Integer>();
        Iterator iterator = tp.entrySet().iterator();
        while(iterator.hasNext()) {
            iterator.next();
        }

        //

    }



}
