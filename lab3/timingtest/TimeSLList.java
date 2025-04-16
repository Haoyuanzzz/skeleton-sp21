package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        int M = 10000;
        for (int N = 1000; N <= 128000; N=N*2) {
            Ns.addLast(N);

            SLList<Integer> testL = new SLList<>();
            for (int i = 1; i <= N; i++) {
                testL.addFirst(i);
            }

            opCounts.addLast(M);
            Stopwatch sw = new Stopwatch();
            for (int i = 0; i < M; i++) {
                int value = testL.getLast();
            }
            double timeInSeconds = sw.elapsedTime();

            times.addLast(timeInSeconds);
        }

        printTimingTable(Ns, times, opCounts);
    }

}
