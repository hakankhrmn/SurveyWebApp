package com.surveyapp.service;

import com.surveyapp.exception.AlreadyExistsException;
import com.surveyapp.exception.NotFoundException;
import com.surveyapp.model.Question;
import com.surveyapp.model.dto.QuestionDto;
import com.surveyapp.repository.QuestionRepository;
import com.surveyapp.repository.SurveyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;
    private final SurveyRepository surveyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, SurveyRepository surveyRepository, ModelMapper modelMapper) {
        this.questionRepository = questionRepository;
        this.surveyRepository = surveyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public QuestionDto getByQuestionId(int questionId) {
        Question question;
        if (questionRepository.findById(questionId).isEmpty()){
            throw new NotFoundException("COULD NOT FOUND THE QUESTION");
        }
        question = questionRepository.findById(questionId).get();
        return modelMapper.map(question,QuestionDto.class);
    }

    @Override
    public QuestionDto createQuestion(int surveyId, String questionText) {
        if (questionRepository.getByQuestionText(questionText)!=null){
            throw new AlreadyExistsException("QUESTION ALREADY EXISTS");
        }
        Question newQuestion = new Question();
        newQuestion.setQuestionText(questionText);
        newQuestion.setSurvey(surveyRepository.getBySurveyId(surveyId));
        return modelMapper.map(questionRepository.save(newQuestion),QuestionDto.class);
    }

    @Override
    public QuestionDto updateQuestion(int questionId, String questionText) {
        Question updateQuestion;
        if (questionRepository.findById(questionId).isEmpty()){
            throw new NotFoundException("COULD NOT FOUND THE QUESTION");
        }
        updateQuestion = questionRepository.getById(questionId);
        updateQuestion.setQuestionText(questionText);
        return modelMapper.map(questionRepository.save(updateQuestion),QuestionDto.class);
    }

    @Override
    public void deleteQuestion(int questionId) {
        if (questionRepository.findById(questionId).isEmpty()){
            throw new NotFoundException("COULD NOT FOUND THE QUESTION");
        }
        questionRepository.deleteById(questionId);
    }
}
