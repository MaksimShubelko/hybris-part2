package com.questions.service.impl;

import com.questions.dao.QuestionDao;
import com.questions.dao.impl.QuestionDaoImpl;
import com.questions.service.QuestionService;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import org.apache.log4j.Logger;
import com.questions.model.QuestionModel;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    @Resource
    private QuestionDao questionDao;

    @Override
    public List<QuestionModel> findQuestionsByMinCreationDate(LocalDateTime minCreationDate) {
        return questionDao.findQuestionsByMinCreationDate(minCreationDate);
    }
}
