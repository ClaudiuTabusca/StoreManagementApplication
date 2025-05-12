package ro.tabusca.storemanagement.service;

import ro.tabusca.storemanagement.model.ProductRequest;
import ro.tabusca.storemanagement.model.ProductResponse;

public interface ProductService {

    public ProductResponse getProductById(String id);
    public ProductResponse addProduct(ProductRequest request);
}
