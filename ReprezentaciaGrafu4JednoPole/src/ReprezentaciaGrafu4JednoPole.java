
import java.util.ArrayList;
import static java.util.Arrays.asList;

public class ReprezentaciaGrafu4JednoPole {
    private static ArrayList<Integer> c;
    private static ArrayList<Integer> v;
    private static ArrayList<Integer> s;
    
    public static void main(String[] args) {
        c = new ArrayList<>(asList(0, 5, 4,  5, 9, 7,  4, 9, 1,  1,  7));
        v = new ArrayList<>(asList(0, 2, 3,  1, 3, 5,  1, 2, 4,  3,  2));
        s = new ArrayList<>(asList(0, 1,     3,        6,        9, 10, 11));
        
        vypisHrany(s, v, c);
    }

    private static void vypisHrany(ArrayList<Integer> s, ArrayList<Integer> v, ArrayList<Integer> c) {
        for (int i = 1; i < s.size() - 1; i++) {
            for (int j = s.get(i); j < s.get(i + 1); j++) {
                System.out.println(i + " --- " + v.get(j) + "  (" + c.get(j) + ")");
            }
        }
    }
    
}
