package ro.tabusca.storemanagement.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.tabusca.storemanagement.entity.Product;
import ro.tabusca.storemanagement.exception.ProductNotFoundException;
import ro.tabusca.storemanagement.model.AllProductsResponse;
import ro.tabusca.storemanagement.model.ChangePriceRequest;
import ro.tabusca.storemanagement.model.ProductRequest;
import ro.tabusca.storemanagement.model.ProductResponse;
import ro.tabusca.storemanagement.repository.ProductRepository;
import ro.tabusca.storemanagement.service.ProductService;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private static final String PRODUCT_NOT_FOUND = "Product not found with id ";

    private ProductRepository productRepository;
    @Override
    public  AllProductsResponse getAllProducts() {
        return AllProductsResponse.builder()
                .products(productRepository.findAll().stream()
                        .map(this::builResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public ProductResponse getProductById(String id) {
        UUID uuid = parseUUID(id);
        return productRepository.findById(uuid)
                .map(this::builResponse)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND + id));
    }

    @Override
    public ProductResponse addProduct(ProductRequest request) {
        Product product = Product.builder()
                .name(request.name())
                .price(request.price())
                .build();
        return builResponse(productRepository.save(product));
    }

    @Override
    @Transactional
    public ProductResponse updatePrice(String id, ChangePriceRequest request) {
        UUID uuid = parseUUID(id);
        Product product = productRepository.findById(uuid)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND + id));
        product.setPrice(request.newPrice());
        return builResponse(productRepository.save(product));
    }

    private ProductResponse builResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId().toString())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
    private UUID parseUUID(String id) {
        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid UUID format: " + id);
        }
    }
}
