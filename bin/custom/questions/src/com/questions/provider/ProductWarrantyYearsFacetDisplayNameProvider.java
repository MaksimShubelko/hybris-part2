package com.questions.provider;

import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractFacetValueDisplayNameProvider;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import org.apache.commons.lang.StringUtils;

public class ProductWarrantyYearsFacetDisplayNameProvider extends AbstractFacetValueDisplayNameProvider {

    private static final String WARRANTY_NOT_PROVIDED_DISPLAY_NAME  = "Warranty not provided";

    private static final String WARRANTY_PROVIDED_WARRANTY_DISPLAY_NAME = "%s year('s) warranty";

    @Override
    public String getDisplayName(SearchQuery searchQuery, IndexedProperty indexedProperty, String facetValue) {
        if (StringUtils.isNotBlank(facetValue))
        {
            if (facetValue.equals("0")) {
                return WARRANTY_NOT_PROVIDED_DISPLAY_NAME;
            }

            if (Integer.parseInt(facetValue) > 0) {
                return String.format(WARRANTY_PROVIDED_WARRANTY_DISPLAY_NAME, facetValue);
            }
        }

        return StringUtils.EMPTY;
    }
}
