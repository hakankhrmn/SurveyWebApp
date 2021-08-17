package com.surveyapp.dao;

import com.surveyapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    User getUserByUserMail(String userMail);
}
