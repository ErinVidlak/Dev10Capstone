package learn.inventory.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class ProductMaterial {

    @Positive(message = "Product must already exists")
    private int productId;

    @Positive(message="Quantity of material used must be greater than 0")
    private int materialQuantity;

    @NotNull(message = "Material cannot be null")
    private Material material;
}
