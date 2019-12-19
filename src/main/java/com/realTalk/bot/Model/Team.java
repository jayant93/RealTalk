package com.realTalk.bot.Model;

import javax.persistence.*;

@Entity
@Table(name="Team_details")
public class Team {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;


    @OneToOne(fetch=FetchType.EAGER, optional = false)
    //@JoinColumn(name="teamMemberEmployee",referencedColumnName = "id",nullable = true)
    private CompanyEmployeeDetails teamMemberEmployee;

    @Column(name="name")
    private String name;

    @ManyToOne
    private Company company;

    @OneToOne(fetch=FetchType.EAGER, optional = false)
  //  @JoinColumn(name="teamHead",referencedColumnName = "id",nullable = true)
    private CompanyEmployeeDetails teamHead;

    @Column(name="start_date")
    private String StartDate;

    public CompanyEmployeeDetails getTeamMemberEmployee() {
        return teamMemberEmployee;
    }

    public void setTeamMemberEmployee(CompanyEmployeeDetails teamMemberEmployee) {
        this.teamMemberEmployee = teamMemberEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public CompanyEmployeeDetails getTeamHead() {
        return teamHead;
    }

    public void setTeamHead(CompanyEmployeeDetails teamHead) {
        this.teamHead = teamHead;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }
}
