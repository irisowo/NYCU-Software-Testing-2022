import java.util.PriorityQueue;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class PriorityQueueTest {
    static Stream<Arguments> streamProvider() {
        return Stream.of(
                Arguments.of(new int[]{3, 2, 1}, new int[]{1, 2, 3}),
                Arguments.of(new int[]{-3, -1, -2, 5}, new int[]{-3, -2, -1, 5}),
                Arguments.of(new int[]{3, -2, -5, -1, 2}, new int[]{-5, -2, -1, 2, 3}),
                Arguments.of(new int[]{-3, 1, 11, 0, 9, 3}, new int[]{-3, 0, 1, 3, 9, 11}),
                Arguments.of(new int[]{3, 7, 2, -1,-2}, new int[]{-2, -1, 2, 3, 7})
        );
    }

    @ParameterizedTest(name = "#{index} - Test with Argument = {0}, {1}")
    @MethodSource("streamProvider")
    public void PriorityQueue_RunTest(int[] random_array, int [] correct_array) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for( int e : random_array)
            pq.add(e);
        for (int e : correct_array) {
            assertEquals(e, pq.poll());
        }
    }

    @Test
    public void whenExceptionThrown_thenInitialCapacityNotGreaterThanOne(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PriorityQueue<Integer> pq = new PriorityQueue<Integer>(0);
        });
    }

    @Test
    public void whenExceptionThrown_thenOfferEisNull(){
        Exception exception = assertThrows(NullPointerException.class, () -> {
            PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
            pq.add(null);
        });
    }

    @Test
    public void whenExceptionThrown_thenCastWrongType(){
        Exception exception = assertThrows(ClassCastException.class, () -> {
            PriorityQueue pq = new PriorityQueue();
            pq.add(0);
            pq.add('0');
        });
    }

}