package org.example.interfaceprac.librarayans;

public abstract class LibrarayItem implements Borrowable {
    protected String title;
    private int ID;
    protected boolean isBorrowed;

    public LibrarayItem(String title, int ID) {
        this.title = title;
        this.ID = ID;
        isBorrowed = false;
    }
}
