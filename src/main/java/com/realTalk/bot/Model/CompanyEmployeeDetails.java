package com.realTalk.bot.Model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="employee_details")
public class CompanyEmployeeDetails {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name="slack_id")
    private String SlackId;

    @Column(name = "nickname")
    private String nickname;

    @ManyToOne
    private Company company;

    @Column(name="email")
    private String Email;

    @Column(name="full_name")
    private String fullName;

    @OneToMany(mappedBy = "employee")
    List<Answers> answers;


    @ManyToOne
    private Team team;


//    @OneToOne(fetch=FetchType.EAGER, optional = false)
//    //@JoinColumn(name="parentEmployee", referencedColumnName="id", nullable = true)
//    private CompanyEmployeeDetails parentEmployee;
//

    @OneToOne
    private EmployeePosition position;

    @Column(name="remote_status")
    private Boolean remoteStatus;

    @Column(name="start_date_at_company")
    private String startDateAtCompany;

    @Column(name="Manager")
    private String Manager;

    @Column(name="location")
    private String Location;

    public String getSlackId() {
        return SlackId;
    }

    public void setSlackId(String slackId) {
        SlackId = slackId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getManager() {
        return Manager;
    }

    public void setManager(String manager) {
        Manager = manager;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EmployeePosition getPosition() {
        return position;
    }

    public void setPosition(EmployeePosition position) {
        this.position = position;
    }

    public Boolean getRemoteStatus() {
        return remoteStatus;
    }

    public void setRemoteStatus(Boolean remoteStatus) {
        this.remoteStatus = remoteStatus;
    }

    public String getStartDateAtCompany() {
        return startDateAtCompany;
    }

    public void setStartDateAtCompany(String startDateAtCompany) {
        this.startDateAtCompany = startDateAtCompany;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
