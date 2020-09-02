package org.bankkata.exceptions;

@SuppressWarnings("serial")
public class NonExistingAccountException extends Exception {

	private static final long serialVersionUID = 5225885313869551094L;

	public NonExistingAccountException(long id) {
		super("Account not found with id " + id);
	}

}
