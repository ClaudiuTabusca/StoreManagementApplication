package ro.tabusca.storemanagement.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ChangePriceRequest(
        @NotNull
        @Positive
        BigDecimal newPrice
) {}
