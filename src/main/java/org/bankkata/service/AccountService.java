package org.bankkata.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bankkata.dao.AccountDatas;
import org.bankkata.dao.TransactionDatas;
import org.bankkata.entities.Account;
import org.bankkata.entities.CurrentAccount;
import org.bankkata.entities.SavingAccount;
import org.bankkata.entities.Transaction;
import org.bankkata.exceptions.NegativeAmountException;
import org.bankkata.exceptions.NegativeBalanceException;
import org.bankkata.exceptions.NonExistingAccountException;
import org.bankkata.exceptions.OverDraftLimitException;
import org.bankkata.utils.TransactionType;

public class AccountService {

	private static final Logger LOGGER = Logger.getLogger(AccountService.class.getName());

	static long transactionNumber = 0;

	public synchronized BigDecimal deposit(Account account, BigDecimal amount)
			throws NegativeAmountException, NonExistingAccountException {
		if (amount.doubleValue() < 0) {
			LOGGER.log(Level.SEVERE, new NegativeAmountException(amount).getMessage());
			throw new NegativeAmountException(amount);
		}
		if (account == null) {
			LOGGER.log(Level.SEVERE, new NonExistingAccountException(0L).getMessage());
			throw new NonExistingAccountException(0L);
		}

		Transaction transaction = new Transaction(++transactionNumber, TransactionType.D, LocalDate.now(), amount,
				account);
		TransactionDatas.transactionDatas.add(transaction);

		AccountDatas.accounts.remove(account);
		account.setBalance(account.getBalance().add(amount));
		AccountDatas.accounts.add(account);

		return account.getBalance();
	}

	public synchronized BigDecimal withdrawal(Account account, BigDecimal amount) throws NegativeAmountException,
			NonExistingAccountException, OverDraftLimitException, NegativeBalanceException {
		if (amount.doubleValue() < 0) {
			LOGGER.log(Level.SEVERE, new NegativeAmountException(amount).getMessage());
			throw new NegativeAmountException(amount);
		}
		if (account == null) {
			LOGGER.log(Level.SEVERE, new NonExistingAccountException(0L).getMessage());
			throw new NonExistingAccountException(0L);
		}
		if (account instanceof CurrentAccount) {
			CurrentAccount currentAccount = (CurrentAccount) account;
			if (currentAccount.getOverDraftLimit().add(currentAccount.getBalance()).add(amount.negate())
					.doubleValue() < 0) {
				LOGGER.log(Level.SEVERE, new OverDraftLimitException("Your overdraftlimit is reached").getMessage());
				throw new OverDraftLimitException("Your overdraftlimit is reached");
			}
		}
		if (account instanceof SavingAccount) {
			SavingAccount savingAccount = (SavingAccount) account;
			if (savingAccount.getBalance().add(amount.negate()).doubleValue() < 0) {
				LOGGER.log(Level.SEVERE,
						new NegativeBalanceException("Not enough money to perform this withdrawal").getMessage());
				throw new NegativeBalanceException("Not enough money to perform this withdrawal");
			}
		}

		Transaction transaction = new Transaction(++transactionNumber, TransactionType.W, LocalDate.now(), amount,
				account);
		TransactionDatas.transactionDatas.add(transaction);

		AccountDatas.accounts.remove(account);
		account.setBalance(account.getBalance().add(amount.negate()));
		AccountDatas.accounts.add(account);

		return account.getBalance();
	}

	public Account getAccount(long accountId) throws NonExistingAccountException {

		Optional<Account> account = AccountDatas.accounts.stream().filter(a -> a.getAccountNumber() == accountId)
				.findFirst();
		if (!account.isPresent())
			throw new NonExistingAccountException(accountId);
		else
			return account.get();
	}

	public String accoutStatement(long accountId) throws NonExistingAccountException {

		Account account = this.getAccount(accountId);
		StringBuilder builder = new StringBuilder(account.toString()).append("\n");
		builder.append("---------------------WITHDRAWAL---------------------").append("\n");
		TransactionDatas.transactionDatas.stream()
				.filter(t -> (t.getAccount().getAccountNumber() == account.getAccountNumber()
						&& t.getTransactionType() == TransactionType.W))
				.forEach(t -> builder.append(t.toString()));

		builder.append("---------------------DEPOSIT---------------------").append("\n");
		TransactionDatas.transactionDatas.stream()
				.filter(t -> (t.getAccount().getAccountNumber() == account.getAccountNumber()
						&& t.getTransactionType() == TransactionType.D))
				.forEach(t -> builder.append(t.toString()));

		return builder.toString();
	}
}
