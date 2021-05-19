package learn.inventory.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
@EqualsAndHashCode
public class MaterialPurchase {

    @Getter
    @Setter
    private int purchaseId;

    @Getter
    @Setter
    private BigDecimal purchasePrice;

    @Getter
    @Setter
    private int quantityPurchased;

    @Getter
    @Setter
    private String units;

    @Getter
    @Setter
    private LocalDate datePurchased;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private int materialId;

}
