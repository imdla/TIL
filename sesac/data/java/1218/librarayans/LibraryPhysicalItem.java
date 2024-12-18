package org.example.interfaceprac.librarayans;

public abstract class LibraryPhysicalItem extends LibrarayItem{
    protected String location;

    public LibraryPhysicalItem(String title, int ID, String location) {
        super(title, ID);
        this.location = location;
    }

    abstract void findLocation();
}
