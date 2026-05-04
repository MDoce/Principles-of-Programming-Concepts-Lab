import computations.Computations;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComputationsTest{

    //test fibonacci

    @Test
    void testFibBaseCases(){
        assertEquals(0, Computations.fibonacci(0), //test base case 0
                "fibonacci(0) should return 0");
        assertEquals(1, Computations.fibonacci(1), //test vase case 1
                "fibonacci(1) should return 1");
    }

    @Test
    void testFibValues(){
        assertEquals(1, Computations.fibonacci(2));
        assertEquals(3, Computations.fibonacci(4));
        assertEquals(8, Computations.fibonacci(6));
        assertEquals(21, Computations.fibonacci(8));
        assertEquals(55, Computations.fibonacci(10));
        assertEquals(1134903170, Computations.fibonacci(45));
    }

    @Test
    void testFibNeg(){
        assertThrows(IllegalArgumentException.class, () -> {
            Computations.fibonacci(-1);});
    }

    // test isPrime
    @Test
    void testIsPrimeEdgeCases() {
        assertFalse(Computations.isPrime(0),  "0 is not prime");
        assertFalse(Computations.isPrime(1),  "1 is not prime");
        assertFalse(Computations.isPrime(-1), "Negative numbers are not prime");
        assertFalse(Computations.isPrime(-7), "Negative numbers are not prime");
    }

    @Test
    void testIsPrimVals(){
        assertTrue(Computations.isPrime(7),  "7 is prime");
        assertFalse(Computations.isPrime(100),"100 is not prime");
        assertFalse(Computations.isPrime(6),  "6 is not prime");
        assertTrue(Computations.isPrime(101), "101 is prime");
    }

    //test isEven and isOdd
    @Test
    void testIsEvenPositiveNumbers() {
        assertTrue(Computations.isEven(0),  "0 is even");
        assertTrue(Computations.isEven(100),"100 is even");
    }

    @Test
    void testIsEvenNegativeNumbers() {
        assertTrue(Computations.isEven(-2),  "-2 is even");
        assertTrue(Computations.isEven(-100),"-100 is even");
    }

    @Test
    void testIsEvenOddNumbers() {
        assertFalse(Computations.isEven(1),  "1 is not even");
        assertFalse(Computations.isEven(-7), "-7 is not even");
    }
    @Test
    void testIsOddPositiveNumbers() {
        assertTrue(Computations.isOdd(1),  "1 is odd");
        assertTrue(Computations.isOdd(99), "99 is odd");
    }

    @Test
    void testIsOddNegativeNumbers() {
        assertTrue(Computations.isOdd(-1),  "-1 is odd");
        assertTrue(Computations.isOdd(-99), "-99 is odd");
    }

    @Test
    void testIsOddEvenNumbers() {
        assertFalse(Computations.isOdd(0),   "0 is not odd");
        assertFalse(Computations.isOdd(-10), "-10 is not odd");
    }

    //test celsius
    @Test
    void testToCelsius() {
        assertEquals(0.0,   Computations.toCelsius(32.0),   0.0001); // freezing
        assertEquals(100.0, Computations.toCelsius(212.0),  0.0001); // boiling
        assertEquals(-40.0, Computations.toCelsius(-40.0),  0.0001); // intersect
    }

    //test fahrenheit
    @Test
    void testToFahrenheit() {
        assertEquals(32.0,  Computations.toFahrenheit(0.0),   0.0001); // freezing
        assertEquals(212.0, Computations.toFahrenheit(100.0), 0.0001); // boiling
        assertEquals(-40.0, Computations.toFahrenheit(-40.0), 0.0001); // intersect
    }

    //test C to F to C
    @Test
    void testTempRound() {
        // C -> F -> C should return the original value
        assertEquals(0.0,   Computations.toCelsius(Computations.toFahrenheit(0.0)),   0.0001);
        assertEquals(100.0, Computations.toCelsius(Computations.toFahrenheit(100.0)), 0.0001);
        assertEquals(-40.0, Computations.toCelsius(Computations.toFahrenheit(-40.0)), 0.0001);
    }
}


