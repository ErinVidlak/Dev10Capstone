package learn.inventory.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ProductMaterial {


    private int productId;

    private int materialQuantity;

    private Material material;
}
