package com.questions.provider;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class ProductQuestionsCountProviderTest {

    @Mock
    private FieldNameProvider fieldNameProvider;

    @InjectMocks
    private ProductQuestionsCountProvider productQuestionsCountProvider;

    @Test
    public void getFieldValues() {
        IndexConfig indexConfig = mock(IndexConfig.class);
        IndexedProperty indexedProperty = mock(IndexedProperty.class);
        ProductModel productModel = mock(ProductModel.class);

        productQuestionsCountProvider.getFieldValues(indexConfig, indexedProperty, productModel);

        verify(productModel, times(1)).getQuestions();
    }
}