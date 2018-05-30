package com.brunofr.services.product;

import com.brunofr.pojo.Product;
import com.brunofr.rest.RestClient;
import com.brunofr.rest.request.ProductFilterRequest;
import com.brunofr.rest.request.ProductRequest;
import com.brunofr.rest.request.ProductShortRequest;
import com.brunofr.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Component("productService")
public class ProductService implements IService {
    private static final String URL = "https://app.nextar.com/api/product/?shopCodes=428322";

    @Autowired
    private RestClient restTemplate;

    @Override
    public Product getAll() {
        ProductRequest request = new ProductRequest();
        ProductFilterRequest filter = new ProductFilterRequest();
        ProductShortRequest _short = new ProductShortRequest();
        _short.setProductName("1");
        filter.setPage(1L);
        filter.setLowStock(false);
        filter.setSearch("coca");
        filter.setSort(_short);
        request.setFilter(filter);
        return restTemplate.postForEntity(Product.class, URL, request);
    }

    @Override
    public Object getById(UUID id) {
        return null;
    }

    @Override
    public Object saveOrUpdate(Object o) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
