package be.pxl.budgetplanner.dao;

import be.pxl.budgetplanner.beans.Account;

import java.util.List;

public interface AccountDAO {
    List<Account> getAccounts();
    Account getAccount(int id);
    Account addAccount(Account account);
    Account removeAccount(Account account);
}
