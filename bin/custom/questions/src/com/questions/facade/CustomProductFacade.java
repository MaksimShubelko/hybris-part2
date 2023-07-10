package com.questions.facade;


import com.questions.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;

public interface CustomProductFacade {

    ProductData getProductWithQuestions(ProductModel productModel);
}

