package be.pxl.budgetplanner.jpa;

import be.pxl.budgetplanner.data.Account;
import be.pxl.budgetplanner.data.Payment;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PaymentJPATest {
    private PaymentJPA jpa;

    @Before
    public void setUp() {
        jpa = new PaymentJPA();
    }

    @After
    public void tearDown() throws Exception {
        jpa.close();
    }

    @Test
    public void getPayments_EmptyDB_NoResults() {
        List<Payment> results = jpa.getPayments();
        assertTrue("Result was not empty", results.isEmpty());
    }

    @Test
    public void addPayment_PaymentAdded() {
        Account account = new Account();
        account.setName("account name");

        Payment payment = new Payment();
        payment.setAmount(10f);
        payment.setCurrency("euro");
        payment.setDetail("detail");
        payment.setDate(LocalDateTime.now());
        payment.setAccount(account);

        jpa.addPayment(payment);
        Payment addedPayment = jpa.getPayment(payment.getId());
        assertThat(addedPayment, CoreMatchers.equalTo(payment));
        assertThat(addedPayment.getAccount(), CoreMatchers.equalTo(account));
    }
}
