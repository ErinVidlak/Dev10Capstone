package learn.inventory.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class MaterialProduct {

    private int materialId;

    private int materialQuantity;

    private Product product;

}
