package com.questions.populator;

import com.questions.data.QuestionData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.questions.model.QuestionModel;

public class QuestionPopulator implements Populator<QuestionModel, QuestionData> {

    @Override
    public void populate(QuestionModel questionModel, QuestionData questionData) throws ConversionException {
        questionData.setQuestion(questionModel.getQuestion());
        questionData.setAnswer(questionData.getAnswer());
    }
}

