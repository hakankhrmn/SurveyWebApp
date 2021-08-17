package com.surveyapp.repository;

import com.surveyapp.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey,Integer> {
}
