package learn.inventory.models;

import lombok.*;

import javax.management.remote.JMXServerErrorException;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
public class MaterialPurchase {

   @PositiveOrZero
    private int purchaseId;

   @PositiveOrZero(message = "Purchase price cannot be negative")
    private BigDecimal purchasePrice = new BigDecimal(BigInteger.ZERO);

 //TODO: Material's price per unit generated/calculated value from MaterialPurchases
    @Positive(message = "Quantity purchased must be at least 1")
    private int quantityPurchased;

    @Size(max = 25, message = "Units of measurement cannot be greater than 25 characters.")
    private String units;

    @PastOrPresent(message = "Date of purchase cant be in the future")
    private LocalDate datePurchased;

    //no validation annotation for disallowing a string that is all whitespace
    private String description;

    @Positive(message = "Purchase must be tied to a material")
    private int materialId;

    public MaterialPurchase(){

    }

}
