public class DecimalZip {
    public int solution(int A, int B) {

        int result = 0;

        String sA = new String(new Integer(A).toString());
        String sB = new String(new Integer(B).toString());

        String sR = new String();

        int maxL = Math.max(sA.length(), sB.length());

        for(int i=0; i<maxL; i++) {
            if(sA.length() > i) {
                sR += sA.substring(i, i+1);
            }
            if(sB.length() > i) {
                sR += sB.substring(i, i+1);
            }
        }

        result = new Integer(sR);

        if (result > 1000000000)
            result = -1;

        return result;
    }

    public static void main(String[] args) {
        //new DecimalZip().solution(12, 56);
        new DecimalZip().solution(12345, 678);

    }
}
