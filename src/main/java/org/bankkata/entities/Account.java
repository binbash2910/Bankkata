package org.bankkata.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Account {

	@NotNull
	@Size(min = 10, max = 10)
	private long accountNumber;

	@NotNull
	private LocalDate initialDate;

	@NotNull
	private BigDecimal balance;

	@NotNull
	private Agency agency;

	@NotNull
	private Client client;

	public Account() {

	}

	public Account(long accountNumber, LocalDate initialDate, BigDecimal balance, Agency agency, Client client) {

		this.accountNumber = accountNumber;
		this.initialDate = initialDate;
		this.balance = balance;
		this.agency = agency;
		this.client = client;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public LocalDate getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(LocalDate initialDate) {
		this.initialDate = initialDate;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountNumber ^ (accountNumber >>> 32));
		result = prime * result + ((agency == null) ? 0 : agency.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((initialDate == null) ? 0 : initialDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Account))
			return false;
		Account other = (Account) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (agency == null) {
			if (other.agency != null)
				return false;
		} else if (!agency.equals(other.agency))
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (initialDate == null) {
			if (other.initialDate != null)
				return false;
		} else if (!initialDate.equals(other.initialDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("---------------------ACCOUNT STATEMENT---------------------");
		builder.append("\n");
		builder.append("Account statement date = ");
		builder.append(LocalDate.now());
		builder.append("\n");
		builder.append("Account Number = ");
		builder.append(accountNumber);
		builder.append("\n");
		if (initialDate != null) {
			builder.append("Creation Date = ");
			builder.append(initialDate);
			builder.append("\n");
		}
		if (balance != null) {
			builder.append("Balance = ");
			builder.append(balance);
			builder.append("\n");
		}
		if (agency != null) {
			builder.append("Agency = ");
			builder.append(agency.getAgencyId() + " " + agency.getAgencyName() + " " + agency.getAgencyLocation());
			builder.append("\n");
		}
		if (client != null) {
			builder.append("Client = ");
			builder.append(client.getName() + " " + client.getSurname());
		}
		builder.append("");
		return builder.toString();
	}

}
