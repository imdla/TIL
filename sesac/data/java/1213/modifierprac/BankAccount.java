package org.example.modifierprac;

public class BankAccount {
    private int balance = 0;
    private int password;

    BankAccount(int password) {
        String strPassword = String.valueOf(password);
        if(strPassword.length() == 4) {
            this.password = password;
        } else {
            System.out.println("Password is 4 digits !");
        }
    }

    public int getBalance() {
        return balance;
    }

    public void setBalanceAdd(int money) {
        this.balance += money;
    }

    public void setBalanceMinus(int money) {
        this.balance -= money;
    }

    public int getPassword() {
        return password;
    }

    // 입금
    public void deposit(int money) {
        if (money > 0) {
            setBalanceAdd(money);
            System.out.println("Current balance: " + getBalance());
        } else {
            System.out.println("money is under 0 !");
        }
    }

    // 출금
    public void withdrawal(int money, int password) {
        String strPassword = String.valueOf(password);
        int userPassword = getPassword();
        boolean flag = true;

        if (strPassword.length() != 4 || userPassword != password) {
            flag = false;
            System.out.println("Wrong password !");
        }

        if (flag && money > this.balance) {
            flag = false;
            System.out.println("withdrawal amount is so large !");
        }

        if (flag) {
            setBalanceMinus(money);
            System.out.println("Current balance: " + getBalance());
        }
    }

    // 잔액 조회
    public void showBalance(int password) {
        String strPassword = String.valueOf(password);
        int userPassword = getPassword();
        boolean flag = true;

        if (strPassword.length() != 4 || userPassword != password) {
            flag = false;
            System.out.println("Wrong password !");
        }

        if (flag) {
            System.out.println("Current balance: " + getBalance());
        }
    }
}