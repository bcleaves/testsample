import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Vacation {
    public int solution(int[] A) {
        int result = 0;

        int L = A.length;

        // form the set of non-dups
        ArrayList<Integer> list =  new ArrayList<Integer>();
        for(int i=0; i<L; i++) { list.add(new Integer(A[i])); }
        List unique = list.stream().distinct().collect(Collectors.toList());

        // form a slice starting at beginning and expand until all members are included
        int begin = 0;
        int end = 0;
        int[] slice = Arrays.copyOfRange(A, begin, end);
        end = begin + slice.length;
        int[] candidate = slice;

        while(end < L) {

            while (!hasAll(slice, unique.toArray())) {
                // expand to the right
                slice = Arrays.copyOfRange(A, begin, ++end);
                // check for end of array
                if (end == L) {
                    // check if we have a candidate
                    if (candidate.length == 0)
                        break;
                    else {
                        result = candidate.length;
                        return result;
                    }
                }
            }

            // check for minimum solution
            if (slice.length == unique.size()) {
                result = slice.length;
                return result;
            }

            // remove elements from left until slice no longer contains all members
            while(hasAll(slice, unique.toArray())) {
                candidate = slice;
                slice = Arrays.copyOfRange(A, ++begin, end);
            }

            // we are finished
            if (end == L){
                result = candidate.length;
                return result;
            }
        }

        result = candidate.length;

        return result;
    }

    public static boolean hasAll(int[] data, Object[] inner) {
        boolean result = false;
        // convert
        Integer[] outer = Arrays.stream( data ).boxed().toArray( Integer[]::new );
        result = Arrays.asList(outer).containsAll(Arrays.asList(inner));
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Vacation().solution(new int[] {2,1,1,3}));
        System.out.println(new Vacation().solution(new int[] {1,1,1,2}));
        System.out.println(new Vacation().solution(new int[] {2,1,1,3,2,1,1,3}));
        System.out.println(new Vacation().solution(new int[] {1,2,3,4,1,1,5,1}));
        System.out.println(new Vacation().solution(new int[] {1,2,3,2,1,3,3,2}));
    }
}
