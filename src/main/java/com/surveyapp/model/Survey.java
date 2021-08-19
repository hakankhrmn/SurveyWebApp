package com.surveyapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "surveys")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","questions"})
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="survey_id")
    private int surveyId;

    @Column(name = "survey_topic")
    private String surveyTopic;

    @OneToMany(mappedBy = "survey")
    private List<Question> questions;
}
