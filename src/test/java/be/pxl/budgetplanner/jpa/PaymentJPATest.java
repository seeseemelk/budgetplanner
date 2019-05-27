package be.pxl.budgetplanner.jpa;

import be.pxl.budgetplanner.beans.Account;
import be.pxl.budgetplanner.beans.Label;
import be.pxl.budgetplanner.beans.Payment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PaymentJPATest {
    private PaymentJPA jpa;
    private Account anyAccount;
    private Account anyCounterAccount;
    private Label anyLabel;
    private Payment anyPayment;

    @Before
    public void setUp() {
        jpa = new PaymentJPA("budgetplanner-test");

        anyAccount = new Account();
        anyAccount.setName("Account Name");

        anyCounterAccount = new Account();
        anyCounterAccount.setName("Counter Account Name");

        anyLabel = new Label();
        anyLabel.setName("Label Name");

        anyPayment = new Payment();
        anyPayment.setAmount(10f);
        anyPayment.setCurrency("euro");
        anyPayment.setDetail("Details");
        anyPayment.setDate(LocalDateTime.now());
        anyPayment.setAccount(anyAccount);
        anyPayment.setCounterAccount(anyCounterAccount);
        anyPayment.setLabel(anyLabel);
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
        jpa.addPayment(anyPayment);
        Payment addedPayment = jpa.getPayment(anyPayment.getId());
        assertThat(addedPayment, equalTo(anyPayment));
        assertThat(addedPayment.getAccount(), equalTo(anyAccount));
        assertThat(addedPayment.getCounterAccount(), equalTo(anyCounterAccount));
        assertThat(addedPayment.getLabel(), equalTo(anyLabel));
    }

    @Test
    public void removePayment_PaymentRemoved() {
        assertThat(jpa.addPayment(anyPayment), equalTo(anyPayment));
        assertThat(jpa.removePayment(anyPayment), equalTo(anyPayment));
        assertThat(jpa.getPayment(anyPayment.getId()), nullValue());
    }
}
