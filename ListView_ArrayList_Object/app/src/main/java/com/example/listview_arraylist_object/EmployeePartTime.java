package com.example.listview_arraylist_object;

public class EmployeePartTime extends Employee
{
    @Override
    public double getSalary()
    {
        return 150;
    }
    @Override
    public String toString()
    {
        return super.toString() + " --> PartTime = " + getSalary();
    }
}
