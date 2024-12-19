package org.example.collectionprac.management;

public class Grade {
    public String subject;
    public int score;

    public Grade(String subject, int score) {
        if (subject.equals("korean") || subject.equals("math") || subject.equals("english")) {
            this.subject = subject;
            this.score = score;
        }
    }
}
