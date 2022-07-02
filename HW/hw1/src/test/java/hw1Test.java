import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class hw1Test {

    //----------------------------------------------------
    @Test
    public void f_test_findLast(){
        int x[] = {2, 3, 5};
        int y = 2;
        int expected = 0;
        assertEquals(expected, hw1.findLast(x, y));
    }
    //--------------------------------------------------
    @Test public void f_test_lastZero(){
        int x[] = {0, 1, 0};
        int expected = 2;
        assertEquals(expected, hw1.lastZero(x));
    }

    //----------------------------------------------------
    @Test public void f_test_countPositive(){
        int x[] = {-4, 2, 0, 2};
        int expected = 2;
        assertEquals(expected, hw1.countPositive(x));
    }
    //----------------------------------------------------
    @Test public void f_test_oddOrPos(){
        int x[] = {-3, -2, 0, 1, 4};
        int expected = 3;
        assertEquals(expected, hw1.oddOrPos(x));

    }
    //----------------------------------------------------
}