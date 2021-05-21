package learn.inventory.models;

import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MaterialPurchase {

   @PositiveOrZero
    private int purchaseId;

   @PositiveOrZero
    private BigDecimal purchasePrice;

    @Positive
    private int quantityPurchased;

    @Size(max = 25, message = "Units of measurement cannot be greater than 25 characters.")
    private String units;

    @PastOrPresent(message = "Date of purchase cant be in the future")
    @NotEmpty
    private LocalDate datePurchased;

    @NotEmpty
    private String description;

    @PositiveOrZero
    private int materialId;

    public MaterialPurchase(){

    }

}
