package com.realTalk.bot.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="SubTopics")
public class SubTopics {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @ManyToOne
    private Topics topics;

    @OneToMany(mappedBy = "subTopics")
    private List<Questions> questions;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Topics getTopics() {
        return topics;
    }

    public void setTopics(Topics topics) {
        this.topics = topics;
    }

    public List<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }
}
