package be.pxl.budgetplanner.jpa;

import be.pxl.budgetplanner.beans.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class AccountJPATest
{
	private AccountJPA jpa;

	@Before
	public void setUp()
	{
		jpa = new AccountJPA("budgetplanner-test");
	}

	@After
	public void tearDown() throws Exception
	{
		jpa.close();
	}

	@Test
	public void getAccounts_NoAccounts_EmptyResult()
	{
		List<Account> accounts = jpa.getAccounts();
		assertThat(accounts.isEmpty(), equalTo(true));
	}

	@Test
	public void addAccount_AccountAdded()
	{
		Account account = createAccount();
		List<Account> accounts = jpa.getAccounts();
		assertThat(accounts.size(), is(1));
		assertThat(accounts.get(0), equalTo(account));
	}

	@Test
	public void getAccount_CorrectAccountReturned()
	{
		Account account = createAccount();
		Account receivedAccount = jpa.getAccount(account.getId());
		assertThat(receivedAccount, equalTo(account));
	}

	@Test
	public void removeAccount_AccountRemoved()
	{
		Account account = createAccount();
		assertThat(jpa.removeAccount(account), equalTo(account));
		assertThat(jpa.getAccount(account.getId()), nullValue());
	}

	private Account createAccount()
	{
		Account account = new Account();
		account.setName("John Doe");
		account.setIBAN("BE30 1234 5678 30");
		account.setNumber("12345678");
		assertThat(jpa.addAccount(account), equalTo(account));
		return account;
	}
}
