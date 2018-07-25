public class Rotation {

    public int[] solution(int[] A, int K) {
        int[] result = A.clone();
        for(int i=0; i<K; i++) {
            result = shiftRight(result);
        }
        return result;
    }

    private int[] shiftRight(int[] A) {
        int L = A.length;
        // check for empty array
        if (L == 0) return new int[0];
        int[] result = new int[L];

        result[0] = A[L-1];
        for (int i = 1; i<A.length; i++) {
           result[i] = A[i-1];
        }

        return result;
    }

    public static void main(String[] args) {

        int[] test = new int[] {3, 8, 9, 7, 6};

        int[] result = new Rotation().solution(test, 3);

    }
}
