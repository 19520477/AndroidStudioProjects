package com.example.lab02_customadapter;

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

    public double getSalary()
    {
        return 0;
    }

    @Override
    public String toString() {
        return this.fullname;
    }

    public boolean isManager()
    {
        return check;
    }
}
