package com.surveyapp.model;

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
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="survey_id")
    private int surveyId;

    @Column(name = "survey_topic")
    private String surveyTopic;

    @Column(name = "is_activated")
    private boolean activated;

    @OneToMany(mappedBy = "survey")
    private List<Question> questions;
}
