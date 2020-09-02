package org.bankkata.dao;

import java.util.HashSet;
import java.util.Set;

import org.bankkata.entities.Transaction;

public class TransactionDatas {

	public static Set<Transaction> transactionDatas = null;

	static {
		transactionDatas = new HashSet<Transaction>();
	}
}
