package com.htoo.sai;

class Account {
    private String _name;
    private double _balance;

    public Account(String name, double balance) {
        this._name = name;
        this._balance = balance;
    }

    public String Name() {
        return _name;
    }

    public double Balance() {
        return _balance;
    }

    public boolean Deposit(Double amount) {
        if (amount > 0) {
            _balance += amount;
            return true;
        } else {
            return false;
        }
    }

    public boolean Withdraw(Double amount) throws Exception {
        if (amount > _balance || amount < 0) {
            return false;
        } else {
            _balance -= amount;
            return true;
        }
    }

    public void Display() {
        System.out.println("Account: " + Name() + " || Balance: " + Balance());
    }
}
