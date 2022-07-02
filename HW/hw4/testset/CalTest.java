import org.junit.Assert;
import org.junit.Test;


public class CalTest {
    // Normal year
    @Test
    public void test1() {
        Assert.assertEquals(59, Cal.cal(1, 1, 3, 1, 2022));
    }

    // leap year
    @Test
    public void test2() {
        Assert.assertEquals(121, Cal.cal(1, 1, 5, 1, 2020));
    }

    // (m100 == 0) && (m400 != 0)
    @Test
    public void test3() {
        Assert.assertEquals(181, Cal.cal(1, 1, 7, 1, 2100));
    }

    // (m100 == 0) && (m400 == 0)
    @Test
    public void test4() {
        Assert.assertEquals(244, Cal.cal(1, 1, 9, 1, 2000));
    }

    // kill ROR_22
    @Test
    public void test5() {
        Assert.assertEquals(89, Cal.cal(2, 1, 5, 1, -2100));
    }

    // kill ROR_18
    @Test
    public void test6(){
        Assert.assertEquals(90, Cal.cal(2, 1, 5, 1, -2020));
    }

    // kill ROR_8
    @Test
    public void test7(){
        Assert.assertEquals(89, Cal.cal(2, 1, 5, 1, -2022));
    }

    // kill ROR_7
    @Test
    public void test8(){
        Assert.assertEquals(30, Cal.cal(1, 1, 1, 31, -2000));
    }

    // kill ROR_4
    @Test
	 public void test9(){
        Assert.assertEquals(31, Cal.cal(5, 1, 2, 1, -2020));
    }
}