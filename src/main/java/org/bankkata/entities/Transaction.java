package org.bankkata.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.bankkata.utils.TransactionType;

public class Transaction {

	@NotNull
	private long transactionNumber;

	@NotNull
	private TransactionType transactionType;

	@NotNull
	private LocalDate transactionDate;

	@Min(0)
	private BigDecimal transactionAmount;

	@NotNull
	private Account account;

	public Transaction() {
	}

	public Transaction(long transactionNumber, TransactionType transactionType, LocalDate transactionDate,
			BigDecimal transactionAmount, Account account) {
		this.transactionNumber = transactionNumber;
		this.transactionAmount = transactionAmount;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.account = account;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public long getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(long transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((transactionAmount == null) ? 0 : transactionAmount.hashCode());
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
		result = prime * result + (int) (transactionNumber ^ (transactionNumber >>> 32));
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Transaction))
			return false;
		Transaction other = (Transaction) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (transactionAmount == null) {
			if (other.transactionAmount != null)
				return false;
		} else if (!transactionAmount.equals(other.transactionAmount))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		if (transactionNumber != other.transactionNumber)
			return false;
		if (transactionType != other.transactionType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transaction Number = ");
		builder.append(transactionNumber);
		builder.append("\n");
		if (transactionType != null) {
			builder.append("Transaction Type = ");
			builder.append(transactionType.getType());
			builder.append("\n");
		}
		if (transactionDate != null) {
			builder.append("Transaction Date = ");
			builder.append(transactionDate);
			builder.append("\n");
		}
		if (transactionAmount != null) {
			builder.append("Transaction Amount = ");
			builder.append(transactionAmount);
			builder.append("\n");
		}

		return builder.toString();
	}

}
