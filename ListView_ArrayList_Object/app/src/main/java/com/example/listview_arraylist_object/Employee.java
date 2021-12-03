package com.example.listview_arraylist_object;

public class Employee {
    protected String id;
    protected String name;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getSalary()
    {
        return 0;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.id+" - "+this.name;
    }
}
