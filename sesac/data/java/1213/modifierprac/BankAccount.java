package org.example.modifierprac;

public class BankAccount {
    private int balance = 0;
    private int password;

    // 계좌 생성
    BankAccount(int password) {
        String strPassword = String.valueOf(password);
        if(strPassword.length() == 4) {
            this.password = password;
        } else {
            System.out.println("Password is 4 digits !");
        }
    }

    // 입금
    public void deposit(int money) {
        if (validateAmount(money)) {
            this.balance += money;
            System.out.println("Current balance: " + this.balance);
        } else {
            System.out.println("money is under 0 !");
        }
    }

    // 출금
    public void withdrawal(int money, int password) {
        boolean flag = true;

        if (!validatePassword(password)) {
            flag = false;
            System.out.println("Wrong password !");
        }

        if (flag && money > this.balance && validateAmount(money)) {
            flag = false;
            System.out.println("withdrawal amount is so large !");
        }

        if (flag) {
            this.balance -= money;
            System.out.println("Current balance: " + this.balance);
        }
    }

    // 잔액 조회
    public void showBalance(int password) {
        boolean flag = true;

        if (!validatePassword(password)) {
            flag = false;
            System.out.println("Wrong password !");
        }

        if (flag) {
            System.out.println("Current balance: " + this.balance);
        }
    }

    // 금액 확인
    private boolean validateAmount(int money) {
        return money > 0;
    }

    // 비밀번호 확인
    private boolean validatePassword(int password) {
        String strPassword = String.valueOf(password);
        return (strPassword.length() == 4 && this.password == password);
    }
}