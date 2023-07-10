package com.questions.populator;

import com.questions.data.QuestionData;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.converters.Populator;
import org.junit.Test;
import org.questions.model.QuestionModel;

import static org.junit.Assert.assertEquals;

@UnitTest
public class QuestionPopulatorTest {

    @Test
    public void populate() {
        QuestionModel questionModel = new QuestionModel();
        questionModel.setQuestion("question");
        questionModel.setAnswer("answer");
        Populator<QuestionModel, QuestionData> populator = new QuestionPopulator();
        QuestionData questionData = new QuestionData();

        populator.populate(questionModel, questionData);

        assertEquals("question", questionData.getQuestion());
        assertEquals("answer", questionData.getAnswer());
    }
}