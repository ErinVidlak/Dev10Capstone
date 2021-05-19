package learn.inventory.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

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
    private String quantityUnits;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private int materialId;

}
