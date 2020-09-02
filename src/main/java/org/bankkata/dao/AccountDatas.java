package org.bankkata.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.bankkata.entities.Account;
import org.bankkata.entities.Address;
import org.bankkata.entities.Agency;
import org.bankkata.entities.Client;
import org.bankkata.entities.CurrentAccount;
import org.bankkata.entities.SavingAccount;

public class AccountDatas {

	public static Set<Account> accounts = null;

	static {
		accounts = new HashSet<Account>();
		accounts.add(new CurrentAccount(0000000001L, LocalDate.now(), new BigDecimal(0), new BigDecimal(1000),
				new Agency(0001, "BANK ONE", "LA DEFENSE"),
				new Client(1111111111, "ETIENNE", "MARCEL", LocalDate.of(1975, 06, 20), 0660701065,
						new Address("etmar@bank.com", "", "PARIS", "750016", "FRANCE"))));

		accounts.add(new SavingAccount(0000000002L, LocalDate.now(), new BigDecimal(1000), 2.25f,
				new Agency(0002, "BANK ONE", "PARIS XVI"),
				new Client(0000000001, "DURAND", "PASCAL", LocalDate.of(1980, 11, 15), 066065234,
						new Address("durpas@bank.com", "", "SERRIS", "77700", "FRANCE"))));
	}
}
