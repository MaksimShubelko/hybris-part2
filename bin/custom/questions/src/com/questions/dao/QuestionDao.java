package com.questions.dao;

import org.questions.model.QuestionModel;

import java.time.LocalDateTime;
import java.util.List;

public interface QuestionDao {

    List<QuestionModel> findQuestionsByMinCreationDate(LocalDateTime minCreationDate);
}
