package learn.inventory.models;

import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
public class ListedProduct {


    @PositiveOrZero()
    private int listedProductId;

    @NotBlank(message = "Listing name is required")
    @Size(max = 50, message = "Listing cannot be more than 50 characters.")
    private String listingName;

    @PositiveOrZero(message = "Price of listing must not be negative")
    private BigDecimal listedPrice;

    @PastOrPresent(message = "Date listed cannot be in the future")
    private LocalDate dateListed;

    //TODO: date sold must come after or be same day as date listed
    @PastOrPresent(message = "Date sold cannot be in the future")
    private LocalDate dateSold;

    private boolean isSold;

    @Positive(message = "Product to list must already exist")
    private int productId;

    //Want to protect against null value for mathematical operations
    @NotNull(message = "Fee amount cannot be null")
    private BigDecimal feeAmount = new BigDecimal(BigInteger.ZERO);


}
