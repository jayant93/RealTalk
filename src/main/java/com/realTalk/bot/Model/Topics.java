package com.realTalk.bot.Model;

import javax.persistence.*;
import java.io.LineNumberInputStream;
import java.util.List;

@Entity
@Table(name="topics")
public class Topics {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @OneToMany(mappedBy="topics")
    private List<SubTopics> subtopics;

    @OneToMany(mappedBy = "topic")
    private List<Questions> questions;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubTopics> getSubtpoics() {
        return subtopics;
    }

    public void setSubtopics(List<SubTopics> subtpoics) {
        this.subtopics = subtpoics;
    }
}
