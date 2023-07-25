package com.questions.service;

import org.questions.model.QuestionModel;

import java.time.LocalDateTime;
import java.util.List;

public interface QuestionService {

    List<QuestionModel> findQuestionsByMinCreationDate(LocalDateTime minCreationDate);
}
