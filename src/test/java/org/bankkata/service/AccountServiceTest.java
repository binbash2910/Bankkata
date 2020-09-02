package org.bankkata.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.bankkata.entities.Account;
import org.bankkata.exceptions.NegativeAmountException;
import org.bankkata.exceptions.NegativeBalanceException;
import org.bankkata.exceptions.NonExistingAccountException;
import org.bankkata.exceptions.OverDraftLimitException;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class AccountServiceTest {

	Account account = new Account();
	AccountService accountService = new AccountService();

	@Test
	@Order(1)
	public void getExistingAccountTest() throws NonExistingAccountException {
		account = accountService.getAccount(0000000001L);
		assertEquals(0000000001L, account.getAccountNumber());
	}

	@Test
	@Order(2)
	public void getNonExistingAccountTest() throws NonExistingAccountException {
		assertThrows(NonExistingAccountException.class, () -> {
			account = accountService.getAccount(0000000000L);
		});
	}

	@Test
	@Order(3)
	public void depositReturnGoodBalanceTest() throws NonExistingAccountException, NegativeAmountException {
		account = accountService.getAccount(0000000001L);
		BigDecimal amount = new BigDecimal(500);
		assertEquals(amount, accountService.deposit(account, amount));
	}

	@Test
	@Order(4)
	public void depositReturnBadBalanceTest() throws NonExistingAccountException, NegativeAmountException {
		account = accountService.getAccount(0000000001L);
		BigDecimal amount = new BigDecimal(500);
		assertNotEquals(502, accountService.deposit(account, amount));
	}

	@Test
	@Order(5)
	public void depositNullAccountTest() throws NonExistingAccountException, NegativeAmountException {
		assertThrows(NonExistingAccountException.class, () -> {
			account = null;
			BigDecimal amount = new BigDecimal(500);
			accountService.deposit(account, amount);
		});
	}

	@Test
	@Order(6)
	public void depositNegativeAmountTest() throws NonExistingAccountException, NegativeAmountException {
		assertThrows(NegativeAmountException.class, () -> {
			account = accountService.getAccount(0000000001L);
			BigDecimal amount = new BigDecimal(-500);
			accountService.deposit(account, amount);
		});
	}

	@Test
	@Order(7)
	public void withdrawalReturnGoodBalanceTest() throws NonExistingAccountException, NegativeAmountException,
			OverDraftLimitException, NegativeBalanceException {
		account = accountService.getAccount(0000000002L);
		BigDecimal amount = new BigDecimal(500);
		assertEquals(new BigDecimal(500), accountService.withdrawal(account, amount));
	}

	@Test
	@Order(8)
	public void withdrawalReturnBadBalanceTest() throws NonExistingAccountException, NegativeAmountException,
			OverDraftLimitException, NegativeBalanceException {
		account = accountService.getAccount(0000000002L);
		BigDecimal amount = new BigDecimal(500);
		assertNotEquals(amount, accountService.withdrawal(account, amount));
	}

	@Test
	@Order(9)
	public void withdrawalNullAccountTest() throws NonExistingAccountException, NegativeAmountException {
		assertThrows(NonExistingAccountException.class, () -> {
			account = null;
			BigDecimal amount = new BigDecimal(500);
			accountService.withdrawal(account, amount);
		});
	}

	@Test
	@Order(10)
	public void withdrawalNegativeAmountTest() throws NonExistingAccountException, NegativeAmountException {
		assertThrows(NegativeAmountException.class, () -> {
			account = accountService.getAccount(0000000002L);
			BigDecimal amount = new BigDecimal(-500);
			accountService.withdrawal(account, amount);
		});
	}

	@Test
	@Order(11)
	public void withdrawalNegativeBalanceTest()
			throws NonExistingAccountException, NegativeAmountException, NegativeBalanceException {
		assertThrows(NegativeBalanceException.class, () -> {
			account = accountService.getAccount(0000000002L);
			BigDecimal amount = new BigDecimal(100);
			accountService.withdrawal(account, amount);
		});
	}

	@Test
	@Order(12)
	public void withdrawalNegativeOverDraftLimitTest() throws OverDraftLimitException {
		assertThrows(OverDraftLimitException.class, () -> {
			account = accountService.getAccount(0000000001L);
			BigDecimal amount = new BigDecimal(5000);
			accountService.withdrawal(account, amount);
		});
	}

	@Test
	@Order(13)
	public void accountStatementTest() throws NonExistingAccountException {
		account = accountService.getAccount(0000000002L);
		assertTrue(accountService.accoutStatement(account.getAccountNumber()).contains("Balance"));
	}

}
