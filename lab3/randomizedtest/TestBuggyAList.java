package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> testAL1 = new AListNoResizing<>();
        BuggyAList<Integer> testAL2 = new BuggyAList<>();
        testAL1.addLast(4);
        testAL2.addLast(4);

        testAL1.addLast(5);
        testAL2.addLast(5);

        testAL1.addLast(6);
        testAL2.addLast(6);

        assertEquals(testAL1.size(), testAL2.size());
        assertEquals(testAL1.removeLast(), testAL2.removeLast());
        assertEquals(testAL1.removeLast(), testAL2.removeLast());
        assertEquals(testAL1.removeLast(), testAL2.removeLast());
    }
    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> L2 = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                L2.addLast(randVal);
                // System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size2 = L2.size();
                // System.out.println("size: " + size);
                assertEquals(size, size2);
            } else if (operationNumber == 2) {
                // getLast
                if(L.size()>0){
                    int lastItem = L.getLast();
                    int lastItem2 = L2.getLast();
                    // System.out.println("getLast(" + lastItem + ")");
                    assertEquals(lastItem, lastItem2);
                }
            } else if (operationNumber == 3) {
                // removeLast
                if(L.size()>0){
                    int lastItem = L.removeLast();
                    int lastItem2 = L2.removeLast();
                    // System.out.println("removeLast(" + lastItem + ")");
                    assertEquals(lastItem, lastItem2);
                }
            }
        }
    }
}
