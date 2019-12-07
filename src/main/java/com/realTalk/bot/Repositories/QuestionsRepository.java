package com.realTalk.bot.Repositories;

import com.realTalk.bot.Model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionsRepository extends JpaRepository<Questions,Long> {

    @Query("Select q from Questions q where QuestionType = :type")
    List<Questions> findAuthQuestions(@Param("type") String type);
}
