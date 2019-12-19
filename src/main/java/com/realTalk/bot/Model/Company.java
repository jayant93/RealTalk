package com.realTalk.bot.Model;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "company_details")
public class Company {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "signup_date")
    private String SignupDate;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "company")
    private List<CompanyEmployeeDetails> employees;

    public String getSignupDate() {
        return SignupDate;
    }

    public void setSignupDate(String signupDate) {
        SignupDate = signupDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<CompanyEmployeeDetails> getEmployees() {
        return employees;
    }

    public void setEmployees(List<CompanyEmployeeDetails> employees) {
        this.employees = employees;
    }
}
