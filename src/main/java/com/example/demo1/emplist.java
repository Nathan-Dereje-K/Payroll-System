package com.example.demo1;

public class emplist {
     String name;
     String email;

     String accountNo;
     String salary;

    public emplist(String name, String email, String accountNo, String salary) {
        this.name = name;
        this.email = email;
        this.accountNo = accountNo;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
