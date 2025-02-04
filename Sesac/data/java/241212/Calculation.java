package org.example;

public class Calculation {
    public int num1;
    public int num2;
    public String oper;

    public Calculation(int num1, int num2, String oper) {
        this.num1 = num1;
        this.num2 = num2;
        this.oper = oper;
    }

    public int Calculate() {
        int ans = 0;
        switch (oper) {
            case "+":
                ans = this.num1 + this.num2;
                break;
            case "-":
                ans = this.num1 - this.num2;
                break;
            case "*":
                ans = this.num1 * this.num2;
                break;
            case "/":
                ans = this.num1 / this.num2;
                break;
        }
        return ans;
    }
}
