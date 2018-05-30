package com.brunofr.rest.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRequest {
    private ProductFilterRequest filter;

    public ProductFilterRequest getFilter() {
        return filter;
    }

    public void setFilter(ProductFilterRequest filter) {
        this.filter = filter;
    }
}
