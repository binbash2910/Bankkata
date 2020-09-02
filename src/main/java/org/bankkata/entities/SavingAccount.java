package org.bankkata.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SavingAccount extends Account {

	private float interestRate;

	public SavingAccount(long accountNumber, LocalDate initialDate, BigDecimal balance, float interestRate,
			Agency agency, Client client) {
		super(accountNumber, initialDate, balance, agency, client);
		this.interestRate = interestRate;
	}

	public SavingAccount() {
	}

	public float getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}
}
