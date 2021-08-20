package com.surveyapp.repository;

import com.surveyapp.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository<Response,Integer> {
    Response getByResponse(String response);
}
