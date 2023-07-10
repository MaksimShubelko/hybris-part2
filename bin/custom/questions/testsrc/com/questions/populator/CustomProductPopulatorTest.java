package com.questions.populator;

import com.questions.data.ProductData;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@UnitTest
public class CustomProductPopulatorTest {

    @Test
    public void populate() {
        ProductModel productModel = new ProductModel();
        productModel.setName("test");
        Populator<ProductModel, ProductData> populator = new CustomProductPopulator();
        ProductData productData = new ProductData();
        populator.populate(productModel, productData);

        assertEquals("test", productData.getName());
    }
}