package com.questions.provider;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductWarrantyYearsProvider implements FieldValueProvider, Serializable {

    @Resource(name = "solrFieldNameProvider")
    private FieldNameProvider fieldNameProvider;

    @Override
    public Collection<FieldValue> getFieldValues(IndexConfig indexConfig,
                                                 IndexedProperty indexedProperty, Object o) throws FieldValueProviderException {
        if (o instanceof ProductModel productModel) {
            return createFieldValue(productModel, indexedProperty);
        }

        throw new FieldValueProviderException("Does not match the type ProductModel");
    }

    private Collection<FieldValue> createFieldValue(ProductModel productModel, IndexedProperty indexedProperty) {
        final List<FieldValue> fieldValues = new ArrayList<>();
        final Integer warrantyYears = getWarrantyYears(productModel);
        addFieldValues(fieldValues, indexedProperty, warrantyYears);

        return fieldValues;
    }

    private void addFieldValues(List<FieldValue> fieldValues, IndexedProperty indexedProperty, Integer warrantyYears) {
        final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
        fieldNames.stream()
                .map(fn -> new FieldValue(fn, warrantyYears))
                .forEach(fieldValues::add);
    }

    private Integer getWarrantyYears(ProductModel productModel) {
        Integer warrantyYears = productModel.getWarrantyYears();
        return warrantyYears == null ? 0 : warrantyYears;
    }
}
