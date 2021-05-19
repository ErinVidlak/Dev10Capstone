package learn.inventory.models;

import lombok.Getter;
import lombok.Setter;

public class MaterialInventory {

    @Getter
    @Setter
    private int inventoryId;

    @Getter
    @Setter
    private int totalQuantity;

    @Getter
    @Setter
    private int materialId;

}
