import java.lang.reflect.Array;
import java.util.Arrays;

public class SwapArray {

    public boolean solution(int[] A) {
        boolean result = true;

        int L = A.length;

        // check for empty array
        if (L == 0) return false;

        int[] sorted = A.clone();
        Arrays.sort(sorted);

        int count = 0;
        // compare 2 arrays for more than 1 swap
        for(int i=0; i<L; i++) {
            if (A[i] != sorted[i]) {
               count++;
            }
        }

        if (count > 2)
            result = false;

        return result;
    }

    public static void main(String[] args) {
        //new SwapArray().solution(new int[] {1,5,3,3,7});
        new SwapArray().solution(new int[] {1,3,5,3,4});
    }
}
