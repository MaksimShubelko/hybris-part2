package com.questions.populator;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.converters.populator.SearchResultVariantProductPopulator;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;

public class SearchResultWarrantyYearsPopulator extends SearchResultVariantProductPopulator {

    @Override
    public void populate(SearchResultValueData source, ProductData target) {
        super.populate(source, target);
        target.setWarrantyYears(Integer.valueOf(this.getValue(source, "warrantyYears")));
    }
}
