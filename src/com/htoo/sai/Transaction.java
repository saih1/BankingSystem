package com.htoo.sai;

import java.sql.Timestamp;
import java.util.Date;

abstract class Transaction {
    protected double _amount;
    protected boolean _success, _executed, _reversed;
    protected Date _dateStamp;
    protected Timestamp _timeStamp;

    public Transaction(double amount) throws Exception {
        if (amount > 0) this._amount = amount;
        else throw new Exception("ERROR: Amount cannot be a negative value");
    }

    public double Amount() {
        return _amount;
    }

    public abstract boolean Success();

    public abstract void Print();

    public boolean Executed() {
        return _executed;
    }

    public boolean Reversed() {
        return _reversed;
    }

    public Date DateStamp() {
        return _dateStamp;
    }

    public Timestamp TimeStamp()
    {
        _dateStamp = new Date();
        long time = DateStamp().getTime();
        return new Timestamp(time);
    }

    public void Execute() throws Exception {
        if (Executed())
            throw new Exception("ERROR: You have already made this transaction");
        _executed = true;
        _timeStamp = TimeStamp();
    }

    public void Rollback() throws Exception {
        if (Reversed()) throw new Exception("ERROR: Transaction has already been reversed");
        else if (!Executed()) throw new Exception("ERROR: No transaction to reverse!");
        else if (Executed() && !Success()) throw new Exception("ERROR: Cannot reverse a failed transaction.");
        _reversed = true;
        _timeStamp = TimeStamp();
    }
}
