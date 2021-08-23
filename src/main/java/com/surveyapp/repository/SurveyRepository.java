package com.surveyapp.repository;

import com.surveyapp.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<Survey,Integer> {

    Survey getBySurveyId(int surveyId);

    Survey getBySurveyTopic(String surveyTopic);

    List<Survey> getBySurveyTopicContains(String surveyTopic);

    /*
    @Query("select s.surveyTopic from Survey s")
    List<String> getSurveyTopics();
*/
}
