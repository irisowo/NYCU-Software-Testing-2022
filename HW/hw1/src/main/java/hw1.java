
public class hw1 {
    //----------1----------
    public static int findLast (int[] x, int y) {
        for (int i=x.length-1; i >= 0; i--) { //modified
            if (x[i] == y) {
                return i;
            }
        }
        return -1;
    }
    //----------2----------
    public static int lastZero (int[] x) {
        for (int i = x.length - 1; i >= 0 ; i--) { //modified
            if (x[i] == 0) {
                return i;
            }
        }
        return -1;
    }
    //----------3----------
    public static int countPositive (int[] x)
    {
        int count = 0;
        for (int i=0; i < x.length; i++) {
            if (x[i] > 0) { //modified
                count++;
            }
        }
        return count;
    }
    //----------4----------
    public static int oddOrPos(int[] x) {
        int count = 0;
        for (int i = 0; i < x.length; i++) {
            if (x[i]%2 != 0 || x[i] > 0) { //modified
                count++;
            }
        }
        return count;
    }

    
}
