package learn.inventory.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class ProductMaterial {

    @Positive
    private int productId;

    @Positive
    private int materialQuantity;

    @NotNull
    private Material material;
}
