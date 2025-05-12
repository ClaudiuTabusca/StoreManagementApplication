package ro.tabusca.storemanagement.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.tabusca.storemanagement.entity.Product;
import ro.tabusca.storemanagement.exception.ProductNotFoundException;
import ro.tabusca.storemanagement.model.ProductRequest;
import ro.tabusca.storemanagement.model.ProductResponse;
import ro.tabusca.storemanagement.repository.ProductRepository;
import ro.tabusca.storemanagement.service.ProductService;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public ProductResponse getProductById(String id) {
        return parseUUID(id)
                .flatMap(productRepository::findById)
                .map(this::builResponse)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id " + id));
    }

    @Override
    public ProductResponse addProduct(ProductRequest request) {
        Product product = Product.builder()
                .name(request.name())
                .price(request.price())
                .build();
        return builResponse(productRepository.save(product));
    }

    private ProductResponse builResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId().toString())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
    private Optional<UUID> parseUUID(String id) {
        try {
            return Optional.of(UUID.fromString(id));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
