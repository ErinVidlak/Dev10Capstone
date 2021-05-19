package learn.inventory.models;

import lombok.Getter;
import lombok.Setter;

public class MaterialProduct {

    @Getter
    @Setter
    private int materialId;

    @Getter
    @Setter
    private int materialQuantity;
    //TODO: add private Product product as a field
}
