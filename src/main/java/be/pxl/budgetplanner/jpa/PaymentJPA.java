package be.pxl.budgetplanner.jpa;

import be.pxl.budgetplanner.dao.PaymentDAO;
import be.pxl.budgetplanner.data.Account;
import be.pxl.budgetplanner.data.Label;
import be.pxl.budgetplanner.data.Payment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class PaymentJPA implements PaymentDAO, AutoCloseable {
    private EntityManagerFactory emf;
    private EntityManager em;

    public PaymentJPA() {
        emf = Persistence.createEntityManagerFactory("budgetplanner");
        em = emf.createEntityManager();
    }

    public EntityTransaction startTransaction() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        return tx;
    }

    private void closeTransaction(EntityTransaction tx) {
        tx.commit();
    }

    @Override
    public void close() throws Exception {
        em.close();
        emf.close();
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
    public void addPayment(Payment payment) {
        EntityTransaction tx = startTransaction();
        em.persist(payment);
        closeTransaction(tx);
    }

    @Override
    public void removePayment(Payment payment) {
        EntityTransaction tx = startTransaction();
        em.remove(payment);
        closeTransaction(tx);
    }

    @Override
    public List<Payment> getPaymentsByAccount(Account account) {
        EntityTransaction tx = startTransaction();
        List<Payment> payments = em
                .createQuery("select p from Payment p where p.accountId = :id", Payment.class)
                .setParameter(":id", account.getId())
                .getResultList();
        closeTransaction(tx);
        return payments;
    }
}
