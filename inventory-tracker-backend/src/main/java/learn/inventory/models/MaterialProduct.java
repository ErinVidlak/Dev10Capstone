package learn.inventory.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class MaterialProduct {

    @Positive(message = "Material must already exist")
    private int materialId;

    @Positive(message = "Quantity of material must be at least 1")
    private int materialQuantity;

    @NotNull(message = "Product cannot be null")
    private Product product;

}
