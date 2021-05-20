package learn.inventory.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Material {

    @Getter
    @Setter
    private int materialId;

    @Getter
    @Setter
    private String materialName;

    @Getter
    @Setter
    private BigDecimal pricePerUnit;

    @Getter
    @Setter
    private String userId;

    @Getter
    @Setter
    private MaterialInventory inventory;

    @Getter
    @Setter
    private List<MaterialPurchase> purchases = new ArrayList<>();

    @Getter
    @Setter
    private List<ProductMaterial> products = new ArrayList<>();


}
