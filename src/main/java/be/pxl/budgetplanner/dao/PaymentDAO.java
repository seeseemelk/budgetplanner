package be.pxl.budgetplanner.dao;

import be.pxl.budgetplanner.beans.Payment;

import java.util.List;

public interface PaymentDAO {
    List<Payment> getPayments();
    Payment getPayment(int id);
    Payment addPayment(Payment payment);
    Payment removePayment(Payment payment);
}
