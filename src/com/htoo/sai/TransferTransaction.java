package com.htoo.sai;

public class TransferTransaction extends Transaction {
    private final DepositTransaction _deposit;
    private final WithdrawTransaction _withdraw;

    public TransferTransaction(Account accountFrom, Account accountTo, double amount) throws Exception {
        super(amount);
        _deposit = new DepositTransaction(accountTo, amount);
        _withdraw = new WithdrawTransaction(accountFrom, amount);
    }

    public Account AccountTo() {
        return _deposit.Account();
    }

    public Account AccountFrom() {
        return _withdraw.Account();
    }

    @Override
    public boolean Success() {
        return _deposit.Success() && _withdraw.Success();
    }

    @Override
    public void Print() {
        if (Success()) {
            System.out.println("Successful transfer transaction");
            System.out.println(_timeStamp);
            System.out.println(Amount() + " has been transferred from "
                    + AccountFrom().Name() + " to "
                    + AccountTo().Name());
        }
    }

    @Override
    public void Execute() throws Exception {
        super.Execute();
        _withdraw.Execute();
        if (_withdraw.Success()) {
            _deposit.Execute();
            if (!_deposit.Success())
                _withdraw.Rollback();
        }
    }

    @Override
    public void Rollback() throws Exception {
        super.Rollback();
        _deposit.Rollback();
        if (_deposit.Reversed()) {
            _withdraw.Rollback();
            if (_withdraw.Reversed()) {
                _reversed = true;
                System.out.println("Transaction successfully reversed");
            } else System.out.println("ERROR: Unable to reverse the transaction");
        }
    }
}
