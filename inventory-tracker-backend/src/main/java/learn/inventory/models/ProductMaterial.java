package learn.inventory.models;

import lombok.Getter;
import lombok.Setter;

public class ProductMaterial {

    @Getter
    @Setter
    private int productId;

    @Getter
    @Setter
    private int materialQuantity;

    @Getter
    @Setter
    private Material material;
}
