package com.surveyapp.repository;

import com.surveyapp.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<Survey,Integer> {

    @Query("select s from Survey s where s.activated = true")
    List<Survey> getAllActiveSurveys();

    @Query("select s from Survey s where s.activated = false")
    List<Survey> getAllNonActiveSurveys();

    Survey getBySurveyId(int surveyId);

    Survey getBySurveyTopic(String surveyTopic);

    List<Survey> getBySurveyTopicContainsAndActivatedTrue(String surveyTopic);


}
