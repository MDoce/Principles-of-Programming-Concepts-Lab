import bankaccount.BankAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** These are imports you may or may not need depending
on where and how you are running the tests

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

*/

class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        // Starts each test with a fresh account of 100.0
        account = new BankAccount(100.0);
    }

    /** 1. Add an @AfterEach annotation and method to delete the current bank account to make it available for garbage collection */
    @AfterEach
    void tearDown() {
        account = null;
    }

    @Test
    void testDeposit() {
    /** 2. Adeposit $50 and check that the balance is 150 */
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance(), 0.001, "Balance should be 150.0 after depositing 50.0 into a 100.0 account");
    }

    @Test
    void testWithdraw() {
    /** 3. withdraw $40 and check that the balance is $60; remember that each test is done on a fresh instance of bank account */
        account.withdraw(40.0);
        assertEquals(60.0, account.getBalance(), 0.001,
                "Balance should be 60.0 after withdrawing 40.0 from a 100.0 account");
    }

    @Test
    void testInvalidDeposit() {
    /** 4. Deposit a negative amount and check if an exception is thrown */
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-50.0),
                "Depositing a negative amount should throw IllegalArgumentException");
    }

    @Test
    void testOverdraft() {
    /** 5. Verify that Withdrawing more than the current balance
    throws an exception */
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(200.0),
                "Withdrawing more than the balance should throw IllegalArgumentException");
    }

    @Test
    /** 6. Add a test to check that an Exception is thrown when
    trying to create a new bankaccout with a negaive initial balance */
    void testNegativeInitialBalance() {   // <-- this line is missing
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(-50.0),
                "Creating an account with a negative balance should throw IllegalArgumentException");
    }
    @Test
    // 1C: Testing transfer
    void testTransfer() {
        BankAccount target = new BankAccount(50.0);
        account.transfer(target, 30.0);
        assertEquals(70.0, account.getBalance(), 0.001,
                "Source account should be 70.0 after transferring 30.0 out of 100.0");
        assertEquals(80.0, target.getBalance(), 0.001,
                "Target account should be 80.0 after receiving 30.0 into 50.0");
    }
}
