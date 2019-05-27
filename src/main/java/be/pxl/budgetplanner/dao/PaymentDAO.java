package be.pxl.budgetplanner.dao;

import be.pxl.budgetplanner.data.Account;
import be.pxl.budgetplanner.data.Payment;

import java.util.List;
import java.util.Set;

public interface PaymentDAO {
    List<Payment> getPayments();
    Payment getPayment(int id);
    List<Payment> getPaymentsByAccount(Account account);

    void addPayment(Payment payment);

    void removePayment(Payment payment);
}
