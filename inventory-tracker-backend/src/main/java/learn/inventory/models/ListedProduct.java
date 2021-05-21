package learn.inventory.models;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ListedProduct {


    private int listedProductId;

    private String listingName;

    private BigDecimal listedPrice;

    private LocalDate dateListed;

    private LocalDate dateSold;

    private boolean isSold;

    private int productId;

    private BigDecimal feeAmount;
}
