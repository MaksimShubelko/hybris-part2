package com.questions.facade;


import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;

public interface CustomProductFacade {

    ProductData getProductWithQuestions(ProductModel productModel);
}

