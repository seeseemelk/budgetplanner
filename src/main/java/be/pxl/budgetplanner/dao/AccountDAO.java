package be.pxl.budgetplanner.dao;

import be.pxl.budgetplanner.data.Account;

import java.util.List;

public interface AccountDAO {
    List<Account> getAccounts();
}
