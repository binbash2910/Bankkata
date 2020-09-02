package org.bankkata.application;

import java.math.BigDecimal;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.bankkata.entities.Account;
import org.bankkata.exceptions.NegativeAmountException;
import org.bankkata.exceptions.NegativeBalanceException;
import org.bankkata.exceptions.NonExistingAccountException;
import org.bankkata.exceptions.OverDraftLimitException;
import org.bankkata.service.AccountService;

public class BankkataApplication {

	private static final Logger LOGGER = Logger.getLogger(BankkataApplication.class.getName());

	public static void main(String[] args) throws NonExistingAccountException, NegativeAmountException,
			OverDraftLimitException, NegativeBalanceException {

		AccountService accountService = new AccountService();
		Account account = accountService.getAccount(0000000001L);
		LOGGER.log(Level.INFO, "-----------BANK KATA-------------");
		LOGGER.log(Level.INFO, "-----------ONE ACCOUNT STATEMENT-------------");
		LOGGER.log(Level.INFO, account.toString());

		LOGGER.log(Level.INFO, "-----------ONE DEPOSIT -------------");
		BigDecimal balance = accountService.deposit(account, new BigDecimal(1000));
		LOGGER.log(Level.INFO, "-----------ACCOUNT STATEMENT AFTER ONE DEPOSIT-------------");
		LOGGER.log(Level.INFO, balance.toString());

		LOGGER.log(Level.INFO, "-----------ONE WITHDRAWAL -------------");
		balance = accountService.withdrawal(account, new BigDecimal(500));
		LOGGER.log(Level.INFO, "-----------ACCOUNT STATEMENT AFTER ONE WITHDRAWAL-------------");
		LOGGER.log(Level.INFO, balance.toString());

		LOGGER.log(Level.INFO, "-----------ACCOUNT STATEMENT AFTER ALL TRANSACTIONS-------------");
		LOGGER.log(Level.INFO, accountService.accoutStatement(account.getAccountNumber()));

	}

	static {
		try {
			FileHandler fileHandler = new FileHandler("bankkatalogs.log");
			fileHandler.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(fileHandler);
		} catch (Exception exception) {
			LOGGER.log(Level.SEVERE, "Cannot read configuration file", exception);
		}
	}

}
