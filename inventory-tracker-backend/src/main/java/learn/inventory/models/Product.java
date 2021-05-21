package learn.inventory.models;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Product {


    @PositiveOrZero
    private int productId;

    @NotBlank
    @Size(max = 50, message = "Product cannot be more than 50 characters.")
    private String productName;

    @PositiveOrZero
    private BigDecimal totalMaterialsCost;

    @PositiveOrZero
    private int timeToMake;

    @NotBlank
    private String userId;

    private  ListedProduct listedProduct;

    private List<ProductMaterial> materials = new ArrayList<>();




}
