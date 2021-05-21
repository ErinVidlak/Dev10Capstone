package learn.inventory.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Material {


    @PositiveOrZero
    private int materialId;

    @NotBlank(message = "Material name is required")
    @Size(max = 50, message = "Material cannot be more than 50 characters.")
    private String materialName;

    //TODO: price per unit is a generated value from MaterialPurchases
    @PositiveOrZero(message = "Price per material unit cannot be negative")
    private BigDecimal pricePerUnit;

    //user ID will always remain constant
    @NotBlank(message = "UserId is required")
    private String userId;

    @NotNull(message = "Material Inventory cannot be null")
    private MaterialInventory inventory = new MaterialInventory();

    private List<MaterialPurchase> purchases = new ArrayList<>();

    private List<MaterialProduct> products = new ArrayList<>();




}
