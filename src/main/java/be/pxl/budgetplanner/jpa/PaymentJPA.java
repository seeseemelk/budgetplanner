package be.pxl.budgetplanner.jpa;

import be.pxl.budgetplanner.dao.PaymentDAO;
import be.pxl.budgetplanner.beans.Payment;

import javax.persistence.EntityTransaction;
import java.util.List;

public class PaymentJPA extends BaseJPA implements PaymentDAO {
    public PaymentJPA()
    {
    }

    public PaymentJPA(String persistenceUnitName)
    {
        super(persistenceUnitName);
    }

    @Override
    public List<Payment> getPayments() {
        EntityTransaction tx = startTransaction();
        List<Payment> payment = em.createQuery("from Payment", Payment.class).getResultList();
        closeTransaction(tx);
        return payment;
    }

    @Override
    public Payment getPayment(int id) {
        EntityTransaction tx = startTransaction();
        Payment payment = em.find(Payment.class, id);
        closeTransaction(tx);
        return payment;
    }

    @Override
    public Payment addPayment(Payment payment) {
        EntityTransaction tx = startTransaction();
        em.persist(payment);
        closeTransaction(tx);
        return payment;
    }

    @Override
    public Payment removePayment(Payment payment) {
        EntityTransaction tx = startTransaction();
        em.remove(payment);
        closeTransaction(tx);
        return payment;
    }

    /*@Override
    public List<Payment> getPaymentsByAccount(Account account) {
        EntityTransaction tx = startTransaction();
        List<Payment> payments = em
                .createQuery("select p from Payment p where p.accountId = :id", Payment.class)
                .setParameter(":id", account.getId())
                .getResultList();
        closeTransaction(tx);
        return payments;
    }*/
}
