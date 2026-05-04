import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class L15_234 {

    //Q4: If you have three JUnit test methods written in the same testing class and the first one fails its
    //    assertions, will he other methods still be executed?
    // this test will purposefully fail to show that tests are run independently, and if one fails the others still run.
    @Test
    void testFail() {
        fail("purposely failing");
    }

    //Q2: fail if any of the values are less than 20
    // The array used should pass this test
    @Test
    void testAllValLeast20(){
        int values [] = {25, 50, 22, 20};

        for (int value : values) {
            assertTrue(value >= 20, "All values should be at least 20 but found value: " + value);
        }
    }

    //Q3: pass if the two Strings contain the same characters
    // the strings used should pass this test
    @Test
    void testSameChars() {
        String strOne = "hello";
        String strTwo = "ohlel";

        char[] a1 = strOne.toCharArray();
        char[] a2 = strTwo.toCharArray();

        Arrays.sort(a1);
        Arrays.sort(a2);

        assertArrayEquals(a1, a2, "Strings supposed to have same characters, but: " + a1 + " " + a2);
    }
}
