package ro.tabusca.storemanagement.model;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class ProductResponse {
    String id;
    String name;
    BigDecimal price;
}