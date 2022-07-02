import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// ref : https://cs.gmu.edu/~offutt/sear/ArtifactsFiles/BoundedQueueTest.java
class BoundedQueueTest {

    //==========================================================
    /* BoundedQueue : C1 C2 */
    // case 1 : base TF
    // Constructor, capacity >=0
    @Test
    void BoundedQueue_TF(){
        try {
            new BoundedQueue(1);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    /* BoundedQueue : C1 C2 */
    // case 2 : TT
    // Constructor, capacity < 0
    @Test
    void BoundedQueue_TT (){
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new BoundedQueue(-1);
        });
    }

    //==========================================================
    /* enQueue : C2 C3 C4 C7 */
    // case 1 : base FTFF
    // capacity >= 0, Make o the newest, o != null, queue is not full
    @Test
    void enQueue_FTFF() {
        BoundedQueue BQ = new BoundedQueue(3);
        BQ.enQueue(1);
        assertEquals("[1]",BQ.toString());
    }

    /* enQueue : C2 C3 C4 C7 */
    // case 2: FFTF
    // capacity >= 0, Fail to make o the newest, o is null, queue is not full
    @Test
    void enQueue_FFTF() {
        BoundedQueue BQ = new BoundedQueue(3);
        Exception e = assertThrows(NullPointerException.class, () -> {
            BQ.enQueue(null);
        });
    }

    /* enQueue : C2 C3 C4 C7 */
    // case 3: FFFT
    // capacity >= 0, Fail to make o the newest, o != null, queue is full
    @Test
    void enQueue_FFFT() {
        BoundedQueue BQ = new BoundedQueue(1);
        BQ.enQueue(1);
        Exception e = assertThrows(IllegalStateException.class, () -> {
            BQ.enQueue(2);
        });
    }

    //==========================================================
    /* deQueue : C2 C5 C6 */
    // case 1: base FTF
    // capacity >= 0, Remove and return oldest, queue is not empty
    @Test
    void deQueue_FTF() {
        BoundedQueue BQ = new BoundedQueue(3);
        BQ.enQueue(1);
        BQ.enQueue(2);
        Object oldest = BQ.deQueue();
        assertEquals("1",oldest.toString());
        assertEquals("[2]",BQ.toString());
    }

    /* deQueue : C2 C5 C6 */
    // case 2: FFT
    // capacity >= 0, Fail to remove and return oldest, queue is empty
    @Test
    void deQueue_FFT() {
        BoundedQueue BQ = new BoundedQueue(3);
        Exception e = assertThrows(IllegalStateException.class, () -> {
            BQ.deQueue();
        });
    }

    //==========================================================
    /* isEmpty : C2 C6 */
    // case 1: base FF
    // capacity >= 0, queue is not empty
    @Test
    void isEmpty_FF() {
        BoundedQueue BQ = new BoundedQueue(3);
        BQ.enQueue(1);
        assertFalse(BQ.isEmpty());
    }

    /* isEmpty : C2 C6 */
    // case 2: FT
    // capacity >= 0, queue is empty
    @Test
    void isEmpty_FT() {
        BoundedQueue BQ = new BoundedQueue(3);
        assertTrue(BQ.isEmpty());
    }

    //==========================================================
    /* isFull : C2 C7 */
    // case 1: base FF
    // capacity >= 0, queue is not full
    @Test
    void isFull_FF() {
        BoundedQueue BQ = new BoundedQueue(3);
        BQ.enQueue(1);
        BQ.enQueue(2);
        assertFalse(BQ.isFull());
    }

    /* isFull : C2 C6 */
    // case 2: FT
    // capacity >= 0, queue is full
    @Test
    void isFull_FT() {
        BoundedQueue BQ = new BoundedQueue(2);
        BQ.enQueue(1);
        BQ.enQueue(2);
        assertTrue(BQ.isFull());
    }

}