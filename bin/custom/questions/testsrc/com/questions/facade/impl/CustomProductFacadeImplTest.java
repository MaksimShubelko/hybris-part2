package com.questions.facade.impl;

import com.questions.data.ProductData;
import com.questions.facade.CustomProductFacade;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.product.ProductModel;
import org.junit.Before;
import org.junit.Test;
import org.questions.model.QuestionModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@UnitTest
public class CustomProductFacadeImplTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getProductWithQuestions() {
        ProductModel productModel = new ProductModel();
        productModel.setName("test");
        QuestionModel questionModel = new QuestionModel();
        questionModel.setQuestion("question");
        questionModel.setAnswer("answer");
        productModel.getQuestions().add(questionModel);
        CustomProductFacade facade = new CustomProductFacadeImpl();

        ProductData productData = facade.getProductWithQuestions(productModel);

        assertNotNull(productData);
        assertEquals(1, productData.getQuestions().size());
        assertEquals("name", productData.getName());
        assertEquals("question", productData.getQuestions().get(0).getQuestion());
        assertEquals("answer", productData.getQuestions().get(0).getAnswer());

    }
}