package com.example.listview_arraylist_object;

public class EmployeeFullTime extends Employee
{
    @Override
    public double getSalary()
    {
        return 500;
    }
    @Override
    public String toString()
    {
        return super.toString() + " --> FullTime = " + getSalary();
    }
}
