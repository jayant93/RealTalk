package com.realTalk.bot.Repositories;

import com.realTalk.bot.Model.QuestionsResponses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionResponseRepository extends JpaRepository<QuestionsResponses,Long> {
}
