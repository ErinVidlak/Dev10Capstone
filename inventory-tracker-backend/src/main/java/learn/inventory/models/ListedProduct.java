package learn.inventory.models;

import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ListedProduct {


    @PositiveOrZero
    private int listedProductId;

    @NotBlank
    @Size(max = 50, message = "Listing cannot be more than 50 characters.")
    private String listingName;

    @PositiveOrZero
    private BigDecimal listedPrice;

    @PastOrPresent
    private LocalDate dateListed;

    //TODO: date sold must come after or be same day as date listed
    @PastOrPresent
    private LocalDate dateSold;

    private boolean isSold;

    @Positive
    private int productId;

    //TODO: fee can be positive or negative, and should start as ZERO to avoid null
    private BigDecimal feeAmount;
}
