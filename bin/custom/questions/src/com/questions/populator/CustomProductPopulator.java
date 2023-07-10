package com.questions.populator;


import com.questions.data.ProductData;
import com.questions.data.QuestionData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class CustomProductPopulator implements Populator<ProductModel, ProductData> {

    @Resource
    private QuestionPopulator questionPopulator;

    @Override
    public void populate(ProductModel productModel, ProductData productData) throws ConversionException {
        productData.setName(productModel.getName());
        List<QuestionData> questions = new ArrayList<>();
        productModel.getQuestions().forEach(qm -> {
            QuestionData questionData = new QuestionData();
            questionPopulator.populate(qm, questionData);
            questions.add(questionData);
        });
        productData.setQuestions(questions);
    }
}
