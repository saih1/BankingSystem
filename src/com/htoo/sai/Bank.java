package com.htoo.sai;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private final List<Account> _accounts;
    private final List<Transaction> _transactions;

    public Bank() {
        _accounts = new ArrayList<>();
        _transactions = new ArrayList<>();
    }

    public void AddAccount(Account account) {
        _accounts.add(account);
    }

    public Account GetAccount(String accountName) {
        for (Account account: _accounts) {
            if (account.Name().equals(accountName))
                return account;
        } return null;
    }

    public Transaction GetTransaction(int index) {
        if (index >= 0 && index <_transactions.size())
            return _transactions.get(index);
        else throw new ArrayIndexOutOfBoundsException();
    }

    public void RollbackTransaction(Transaction transaction) throws Exception {
        transaction.Rollback();
    }

    public void ExecuteTransaction(Transaction transaction) throws Exception {
        _transactions.add(transaction);
        transaction.Execute();
    }

    public void PrintTransactionHistory() {
        for (int i = 0; i < _transactions.size(); i++) {
            System.out.println("------------------------");
            System.out.println("Transaction number: " + i);
            _transactions.get(i).Print();
        }
    }
}
