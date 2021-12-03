package com.example.lab3_b2;

public class Contact
{
    private int id;
    private String name;
    private final String phoneNumber;

    public Contact(String name, String phoneNumber)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getId()
    {
        return id;
    }

    public Contact(int id, String name, String phoneNumber)
    {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

}

