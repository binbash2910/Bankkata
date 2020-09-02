package org.bankkata.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CurrentAccount extends Account {

	private BigDecimal overDraftLimit;

	public CurrentAccount() {
		super();
	}

	public CurrentAccount(long accountNumber, LocalDate initialDate, BigDecimal balance, BigDecimal overDraftLimit,
			Agency agency, Client client) {
		super(accountNumber, initialDate, balance, agency, client);
		this.overDraftLimit = overDraftLimit;
	}

	public BigDecimal getOverDraftLimit() {
		return overDraftLimit;
	}

	public void setOverDraftLimit(BigDecimal overDraftLimit) {
		this.overDraftLimit = overDraftLimit;
	}

}
