package com.questions.provider;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import org.questions.model.QuestionModel;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ProductQuestionsCountProvider implements FieldValueProvider, Serializable {

    @Resource(name = "solrFieldNameProvider")
    private FieldNameProvider fieldNameProvider;

    @Override
    public Collection<FieldValue> getFieldValues(IndexConfig indexConfig,
                                                 IndexedProperty indexedProperty, Object model) {

        final Collection<FieldValue> fieldValues = new ArrayList<>();
        if (model instanceof ProductModel product) {
            fieldValues.addAll(createFieldValue(product, indexedProperty));
        }
        return fieldValues;
    }

    private List<FieldValue> createFieldValue(ProductModel product, IndexedProperty indexedProperty) {
        final List<FieldValue> fieldValues = new ArrayList<>();
        final int questionsCount = getQuestionsCount(product);
        addFieldValues(fieldValues, indexedProperty, questionsCount);
        return fieldValues;
    }

    private void addFieldValues(List<FieldValue> fieldValues, IndexedProperty indexedProperty, int questionsCount) {
        final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
        fieldNames.forEach(fn -> fieldValues.add(new FieldValue(fn, questionsCount)));
    }

    private int getQuestionsCount(ProductModel product) {
        return (int) product.getQuestions()
                .stream()
                .filter(QuestionModel::isApproved)
                .count();
    }
}
