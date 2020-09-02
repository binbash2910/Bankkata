package org.bankkata.application;

import java.math.BigDecimal;

import org.bankkata.entities.Account;
import org.bankkata.exceptions.NegativeAmountException;
import org.bankkata.exceptions.NegativeBalanceException;
import org.bankkata.exceptions.NonExistingAccountException;
import org.bankkata.exceptions.OverDraftLimitException;
import org.bankkata.service.AccountService;

public class BankkataApplication {

	public static void main(String[] args) throws NonExistingAccountException, NegativeAmountException,
			OverDraftLimitException, NegativeBalanceException {

		AccountService accountService = new AccountService();
		Account account = accountService.getAccount(0000000001L);
		System.out.println("-----------BANK KATA-------------");
		System.out.println("-----------ONE ACCOUNT STATEMENT-------------");
		System.out.println(account.toString());

		System.out.println("-----------ONE DEPOSIT -------------");
		BigDecimal balance = accountService.deposit(account, new BigDecimal(1000));
		System.out.println("-----------ACCOUNT STATEMENT AFTER ONE DEPOSIT-------------");
		System.out.println(balance);

		System.out.println("-----------ONE WITHDRAWAL -------------");
		balance = accountService.withdrawal(account, new BigDecimal(500));
		System.out.println("-----------ACCOUNT STATEMENT AFTER ONE WITHDRAWAL-------------");
		System.out.println(balance);

		System.out.println("-----------ACCOUNT STATEMENT AFTER ALL TRANSACTIONS-------------");
		System.out.println(accountService.accoutStatement(account.getAccountNumber()));

	}

}
