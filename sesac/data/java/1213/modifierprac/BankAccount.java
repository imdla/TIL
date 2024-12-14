package org.example.modifierprac;

public class BankAccount {
    private int balance = 0;
    private String password;

    public int getBalance() {
        return balance;
    }

    public void setBalanceAdd(int money) {
        this.balance += money;
    }

    public void setBalanceMinus(int money) {
        this.balance -= money;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.length() == 4) {
            this.password = password;
        } else {
            System.out.println("password is 4 digits !");
        }
    }

    // 입금
    public void deposit(int money) {
        if (money > 0) {
            setBalanceAdd(money);
            System.out.println("balance " + getBalance());
        } else {
            System.out.println("money is under 0 !");
        }
    }

    // 출금
    public void withdrawal(int money, String password) {
        boolean flag = true;
        if (money > this.balance) {
            flag = false;
        }

        if (flag && password.length() == 4) {
            String userPw = getPassword();
            if (!password.equals(userPw)) {
                flag = false;
            }
        } else {
            flag = false;
        }

        if (flag) {
            setBalanceMinus(money);
            System.out.println("balance " + getBalance());
        } else {
            System.out.println("long password !");
        }
    }

    // 잔액 조회
    public void showBalance(String password) {
        boolean flag = true;
        if (password.length() == 4) {
            String userPw = getPassword();
            if (password.equals(userPw)) {
                flag = false;
            }
        } else {
            flag = false;
        }

        if (flag) {
            System.out.println("balance " + getBalance());
        } else {
            System.out.println("long password !");
        }
    }
}