package com.realTalk.bot.Model;

import javax.persistence.*;

@Entity
@Table(name="employee_position")
public class EmployeePosition {


    @Id
    @GeneratedValue
    @Column(name="id")
    Long id;


    @Column(name="position_title")
    private String Title;


    @OneToOne
    private EmployeePosition parent;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public EmployeePosition getParent() {
        return parent;
    }

    public void setParent(EmployeePosition parent) {
        this.parent = parent;
    }
}
