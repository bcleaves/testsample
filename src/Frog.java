public class Frog {

    public int solution(int X, int Y, int D) {

        int distance = Y - X;

        if (distance == 0) return 0;

        int jumps = distance / D;

        // add one more to get beyound target
        int end = X + jumps * D;
        if (end < Y) jumps++;

        return jumps;
    }

    public static void main(String[] args){
        //new Frog().solution(10, 85, 30);
        new Frog().solution(2,11,3);
    }
}
