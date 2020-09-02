package org.bankkata.utils;

public enum TransactionType {

	D("DEPOSIT"), W("WITHDRAWAL");

	private String type;

	TransactionType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
