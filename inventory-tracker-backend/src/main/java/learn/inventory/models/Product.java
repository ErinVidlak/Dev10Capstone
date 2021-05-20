package learn.inventory.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
public class Product {

    @Getter
    @Setter
    private int productId;

    @Getter
    @Setter
    private String productName;

    @Getter
    @Setter
    private BigDecimal totalMaterialsCost;

    @Getter
    @Setter
    private int timeToMake;

    @Getter
    @Setter
    private String userId;

    @Getter
    @Setter
    private List<ProductMaterial> materials = new ArrayList<>();


}
