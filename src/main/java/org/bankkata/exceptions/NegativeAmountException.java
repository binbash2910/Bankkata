package org.bankkata.exceptions;

import java.math.BigDecimal;

public class NegativeAmountException extends Exception {

	public NegativeAmountException() {
		super();
	}

	public NegativeAmountException(BigDecimal amount) {
		super("Amount of transaction can't be negative " + amount.doubleValue());
	}

}
