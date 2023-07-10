package com.questions.facade.impl;


import com.questions.data.ProductData;
import com.questions.facade.CustomProductFacade;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import javax.annotation.Resource;


public class CustomProductFacadeImpl implements CustomProductFacade {

    @Resource
    private Converter<ProductModel, ProductData> customProductConverter;

    @Resource
    private ProductService productService;
    @Override
    public ProductData getProductWithQuestions(ProductModel productModel) {
        return customProductConverter.convert(productModel);
    }

}


