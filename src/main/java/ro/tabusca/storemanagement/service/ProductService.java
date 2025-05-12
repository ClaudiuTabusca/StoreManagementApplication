package ro.tabusca.storemanagement.service;

import ro.tabusca.storemanagement.model.AllProductsResponse;
import ro.tabusca.storemanagement.model.ChangePriceRequest;
import ro.tabusca.storemanagement.model.ProductRequest;
import ro.tabusca.storemanagement.model.ProductResponse;

public interface ProductService {

    AllProductsResponse getAllProducts();
    ProductResponse getProductById(String id);
    ProductResponse addProduct(ProductRequest request);
    ProductResponse updatePrice(String id, ChangePriceRequest request);
}
