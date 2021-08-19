package com.surveyapp.repository;

import com.surveyapp.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey,Integer> {

    Survey getBySurveyId(int surveyId);

    Survey getBySurveyTopic(String surveyTopic);

/*
    @Query("select s.surveyTopic from Survey s")
    List<String> getSurveyTopics();
*/
}
