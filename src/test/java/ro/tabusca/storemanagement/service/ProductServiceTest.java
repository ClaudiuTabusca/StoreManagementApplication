package ro.tabusca.storemanagement.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.tabusca.storemanagement.entity.Product;
import ro.tabusca.storemanagement.exception.ProductNotFoundException;
import ro.tabusca.storemanagement.model.AllProductsResponse;
import ro.tabusca.storemanagement.model.ChangePriceRequest;
import ro.tabusca.storemanagement.model.ProductRequest;
import ro.tabusca.storemanagement.model.ProductResponse;
import ro.tabusca.storemanagement.repository.ProductRepository;
import ro.tabusca.storemanagement.service.impl.ProductServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

        @Mock
        private ProductRepository productRepository;

        @InjectMocks
        private ProductServiceImpl productServiceTest;

        @Test
        void getAllProducts_shouldReturnListOfResponses() {
            UUID id = UUID.randomUUID();
            Product product = Product.builder()
                    .id(id)
                    .name("Mouse")
                    .price(new BigDecimal("99.99"))
                    .build();
            when(productRepository.findAll()).thenReturn(List.of(product));

            AllProductsResponse response = productServiceTest.getAllProducts();

            assertNotNull(response);
            assertEquals(1, response.getProducts().size());
            assertEquals("Mouse", response.getProducts().get(0).getName());
            verify(productRepository).findAll();
        }

        @Test
        void getProductById_shouldReturnProductResponse_whenExists() {
            UUID id = UUID.randomUUID();
            Product product = Product.builder()
                    .id(id)
                    .name("Keyboard")
                    .price(new BigDecimal("129.99"))
                    .build();
            when(productRepository.findById(id)).thenReturn(Optional.of(product));

            ProductResponse response = productServiceTest.getProductById(id.toString());

            assertNotNull(response);
            assertEquals("Keyboard", response.getName());
            assertEquals(id.toString(), response.getId());
            verify(productRepository).findById(id);
        }

        @Test
        void getProductById_shouldThrowException_whenNotFound() {
            UUID id = UUID.randomUUID();
            when(productRepository.findById(id)).thenReturn(Optional.empty());

            assertThrows(ProductNotFoundException.class, () -> productServiceTest.getProductById(id.toString()));
            verify(productRepository).findById(id);
        }

        @Test
        void addProduct_shouldSaveAndReturnResponse() {
            ProductRequest request = new ProductRequest("Laptop", new BigDecimal("4999.99"));
            UUID id = UUID.randomUUID();
            Product savedProduct = Product.builder()
                    .id(id)
                    .name(request.name())
                    .price(request.price())
                    .build();
            when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

            ProductResponse response = productServiceTest.addProduct(request);

            assertNotNull(response);
            assertEquals("Laptop", response.getName());
            assertEquals(id.toString(), response.getId());
            verify(productRepository).save(any(Product.class));
        }

        @Test
        void updatePrice_shouldUpdateAndReturnResponse() {
            UUID id = UUID.randomUUID();
            Product product = Product.builder()
                    .id(id)
                    .name("Monitor")
                    .price(new BigDecimal("600.00"))
                    .build();
            ChangePriceRequest request = new ChangePriceRequest(new BigDecimal("550.00"));
            when(productRepository.findById(id)).thenReturn(Optional.of(product));
            when(productRepository.save(any(Product.class))).thenReturn(product);

            ProductResponse response = productServiceTest.updatePrice(id.toString(), request);

            assertNotNull(response);
            assertEquals("Monitor", response.getName());
            assertEquals(new BigDecimal("550.00"), response.getPrice());
            verify(productRepository).findById(id);
            verify(productRepository).save(product);
        }

        @Test
        void updatePrice_shouldThrowException_whenProductNotFound() {
            UUID id = UUID.randomUUID();
            ChangePriceRequest request = new ChangePriceRequest(new BigDecimal("550.00"));
            when(productRepository.findById(id)).thenReturn(Optional.empty());

            assertThrows(ProductNotFoundException.class, () -> productServiceTest.updatePrice(id.toString(), request));
            verify(productRepository).findById(id);
        }
    }