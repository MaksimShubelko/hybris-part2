package com.questions.populator;

import com.questions.data.QuestionData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

public class QuestionPopulator implements Populator<ProductModel, ProductData> {

    @Override
    public void populate(ProductModel productModel, ProductData productData) throws ConversionException {
        List<QuestionData> questionDataList = new ArrayList<>();
        productModel.getQuestions().forEach(q -> {
            QuestionData questionData = new QuestionData();
            questionData.setQuestion(q.getQuestion());
            questionData.setAnswer(q.getAnswer());
            questionDataList.add(questionData);
        });
        productData.setQuestions(questionDataList);
    }
}

