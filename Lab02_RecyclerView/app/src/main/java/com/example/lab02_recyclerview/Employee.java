package com.example.lab02_recyclerview;

public class Employee {
    protected String id;
    protected String fullname;
    boolean check;
    public  Employee(boolean bool){ check = bool;}

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getFullName()
    {
        return fullname;
    }

    public void setName(String name)
    {
        this.fullname = name;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.fullname;
    }

    public boolean isManager()
    {
        return check;
    }
}
