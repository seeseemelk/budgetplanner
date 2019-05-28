package be.pxl.budgetplanner.jpa;

import be.pxl.budgetplanner.dao.AccountDAO;
import be.pxl.budgetplanner.beans.Account;

import javax.persistence.*;
import java.util.List;

public class AccountJPA extends BaseJPA implements AccountDAO {
    public AccountJPA()
    {
    }

    public AccountJPA(String persistenceUnitName)
    {
        super(persistenceUnitName);
    }

    @Override
    public List<Account> getAccounts()
    {
        EntityTransaction transaction = startTransaction();
        List<Account> accounts = em.createQuery("select a from Account a", Account.class).getResultList();
        closeTransaction(transaction);
        return accounts;
    }

    @Override
    public Account getAccount(int id)
    {
        EntityTransaction transaction = startTransaction();
        Account account = em.find(Account.class, id);
        closeTransaction(transaction);
        return account;
    }

    @Override
    public Account addAccount(Account account)
    {
        EntityTransaction transaction = startTransaction();
        em.persist(account);
        closeTransaction(transaction);
        return account;
    }

    @Override
    public Account removeAccount(Account account)
    {
        EntityTransaction transaction = startTransaction();
        em.remove(account);
        closeTransaction(transaction);
        return account;
    }
}
