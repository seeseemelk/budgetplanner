package be.pxl.budgetplanner.jpa;

import be.pxl.budgetplanner.dao.AccountDAO;
import be.pxl.budgetplanner.data.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class AccountJPA implements AccountDAO {
    //private EntityManagerFactory;

    private void connect()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("budgetplanner");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        tx.commit();
        em.close();
        emf.close();
    }

    @Override
    public List<Account> getAccounts() {
        throw new UnsupportedOperationException("Not yet supported");
    }
}
