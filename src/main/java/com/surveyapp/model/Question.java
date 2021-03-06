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
@Table(name = "questions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","responses"})
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="question_id")
    private int questionId;

    @Column(name = "question")
    private String questionText;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Response> responses;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "survey_id")
    private Survey survey;

}
