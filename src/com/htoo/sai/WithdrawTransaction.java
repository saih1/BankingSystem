package com.htoo.sai;

public class WithdrawTransaction extends Transaction {

    private final Account _account;

    public WithdrawTransaction(Account account, double amount) throws Exception {
        super(amount);
        this._account = account;
    }

    public Account Account() {
        return _account;
    }

    @Override
    public double Amount() {
        return super.Amount();
    }

    @Override
    public boolean Success() {
        return _success;
    }

    @Override
    public void Print() {
        if (Success()) {
            System.out.println("Successful withdraw transaction");
            System.out.println(_timeStamp);
            System.out.println(Amount() + " has been withdrawn from " + Account().Name());
        } else System.out.println("Unsuccessful withdraw transaction");
    }

    public void Execute() throws Exception {
        super.Execute();
        _success = Account().Withdraw(Amount());
    }

    public void Rollback() throws Exception {
        super.Rollback();
        if (Account().Deposit(Amount())) {
            System.out.println("Transaction successfully reversed");
            _reversed = true;
        } else System.out.println("Error: Unable to reverse the transaction");
    }
}
