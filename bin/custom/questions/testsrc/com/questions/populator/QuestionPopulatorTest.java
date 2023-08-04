package com.questions.populator;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import org.junit.Test;
import com.questions.model.QuestionModel;

import static org.junit.Assert.assertEquals;

@UnitTest
public class QuestionPopulatorTest {

    @Test
    public void populate() {
        ProductModel productModel = new ProductModel();
        QuestionModel questionModel = new QuestionModel();
        questionModel.setQuestion("question");
        questionModel.setAnswer("answer");
        productModel.getQuestions().add(questionModel);
        Populator<ProductModel, ProductData> populator = new QuestionPopulator();
        ProductData productData = new ProductData();

        populator.populate(productModel, productData);

        assertEquals("question", productData.getQuestions().get(0).getQuestion());
        assertEquals("answer", productData.getQuestions().get(0).getAnswer());
    }
}