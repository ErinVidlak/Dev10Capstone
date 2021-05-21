package learn.inventory.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class MaterialProduct {

    @Positive
    private int materialId;

    @Positive
    private int materialQuantity;

    @NotNull
    private Product product;

}
