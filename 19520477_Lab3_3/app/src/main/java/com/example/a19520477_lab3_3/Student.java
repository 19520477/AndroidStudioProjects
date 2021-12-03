package com.example.a19520477_lab3_3;

public class Student {
    private int No;
    private String name;
    private String ID;
    private String phoneNumber;
    private String email;

    public Student() {}

    public Student(String name, String id, String phone, String email)
    {
        this.name = name;
        this.ID = id;
        this.phoneNumber = phone;
        this.email = email;
    }

    public int getNo()
    {
        return No;
    }

    public void setNo(int No)
    {
        this.No = No;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getID()
    {
        return this.ID;
    }

    public void setID(String id)
    {
        this.ID = id;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
