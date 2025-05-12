package ro.tabusca.storemanagement.service;

import ro.tabusca.storemanagement.model.ProductRequest;
import ro.tabusca.storemanagement.model.ProductResponse;

public interface ProductService {

    ProductResponse getProductById(String id);
    ProductResponse addProduct(ProductRequest request);
}
