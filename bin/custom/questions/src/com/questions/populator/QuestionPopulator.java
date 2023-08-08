package com.questions.populator;

import com.questions.data.QuestionData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import com.questions.model.QuestionModel;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionPopulator implements Populator<ProductModel, ProductData> {

    @Override
    public void populate(ProductModel productModel, ProductData productData) throws ConversionException {
        List<QuestionData> questionDataList = productModel.getQuestions()
                .stream()
                .map(this::convertQuestions)
                .collect(Collectors.toList());

        productData.setQuestions(questionDataList);
    }

    private QuestionData convertQuestions(QuestionModel questionModel) {
        QuestionData questionData = new QuestionData();
        questionData.setQuestion(questionModel.getQuestion());
        questionData.setAnswer(questionModel.getAnswer());
        questionData.setApproved(questionModel.isApproved());
        return questionData;
    }
}

