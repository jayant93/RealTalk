package com.realTalk.bot.Model;

import javax.persistence.*;


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

    @Column(name="first_name")
    private String FirstName;

    @Column(name="last_name")
    private String LastName;

    @Column(name="is_manager")
    private boolean isManager;


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

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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
