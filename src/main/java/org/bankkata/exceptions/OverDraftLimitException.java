package org.bankkata.exceptions;

public class OverDraftLimitException extends Exception {

	public OverDraftLimitException() {
	}

	public OverDraftLimitException(String message) {
		super(message);
	}

}
