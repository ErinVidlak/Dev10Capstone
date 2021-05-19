package learn.inventory.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Product {

    @Getter
    @Setter
    private String productName;

    @Getter
    @Setter
    private BigDecimal totalMaterialCost;

    @Getter
    @Setter
    private LocalTime timeToMake;

    @Getter
    @Setter
    private String userId;

    @Getter
    @Setter
    private List<ProductMaterial> materials = new ArrayList<>();
}
