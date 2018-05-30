package com.brunofr.rest.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductFilterRequest {
    private String search;
    private Boolean lowStock;
    private ProductShortRequest sort;
    private Long page;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Boolean getLowStock() {
        return lowStock;
    }

    public void setLowStock(Boolean lowStock) {
        this.lowStock = lowStock;
    }

    public ProductShortRequest getSort() {
        return sort;
    }

    public void setSort(ProductShortRequest sort) {
        this.sort = sort;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }
}
