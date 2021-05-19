package learn.inventory.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ListedProduct {

    @Getter
    @Setter
    private int listedProductId;

    @Getter
    @Setter
    private String listingName;

    @Getter
    @Setter
    private BigDecimal listedPrice;

    @Getter
    @Setter
    private LocalDate dateListed;

    @Getter
    @Setter
    private LocalDate dateSold;

    @Getter
    @Setter
    private boolean isSold;

    @Getter
    @Setter
    private int productId;

    @Getter
    @Setter
    private BigDecimal feeAmount;
}
