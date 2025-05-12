package ro.tabusca.storemanagement.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.util.List;

@Value
@Getter
@Builder
public class AllProductsResponse {
    List<ProductResponse> products;
}
