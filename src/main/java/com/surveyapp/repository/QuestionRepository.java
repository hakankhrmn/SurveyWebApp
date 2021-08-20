package com.surveyapp.repository;

import com.surveyapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    Question getByQuestionText(String questionText);
}
