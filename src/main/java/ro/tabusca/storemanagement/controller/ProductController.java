package ro.tabusca.storemanagement.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.tabusca.storemanagement.model.AllProductsResponse;
import ro.tabusca.storemanagement.model.ChangePriceRequest;
import ro.tabusca.storemanagement.model.ProductRequest;
import ro.tabusca.storemanagement.model.ProductResponse;
import ro.tabusca.storemanagement.service.ProductService;

@RestController()
@RequestMapping("products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping("/getAllProducts")
    public AllProductsResponse getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/getProduct/{id}")
    public ProductResponse getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PostMapping("/addProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse addProduct(@Valid @RequestBody ProductRequest request) {
        return productService.addProduct(request);
    }

    @PatchMapping("/changePrice/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse updatePrice(@PathVariable String id,
                                       @Valid @RequestBody ChangePriceRequest request) {
        return productService.updatePrice(id, request);
    }

}
