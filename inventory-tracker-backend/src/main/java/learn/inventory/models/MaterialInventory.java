package learn.inventory.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
public class MaterialInventory {

    @PositiveOrZero
    private int inventoryId;

    private int totalQuantity;

    @Positive
    private int materialId;

}
