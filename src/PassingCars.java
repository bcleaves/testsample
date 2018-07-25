public class PassingCars {
    public int solution(int[] A) {
        int result = 0;

        int L = A.length;

        // check for empty
        if(L == 0)
            return 0;

        for(int i=0; i<L; i++) {
            if(A[i] == 0) {
                for(int j=i; j<L; j++) {
                    if(A[j] == 1) {
                        result++;
                        if (result > 1000000000)
                            return -1;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        new PassingCars().solution(new int[] {0,1,0,1,1});

    }
}
