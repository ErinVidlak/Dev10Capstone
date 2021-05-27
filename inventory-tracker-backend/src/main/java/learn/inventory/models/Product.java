package learn.inventory.models;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
public class Product {


    @PositiveOrZero
    private int productId;

    @NotBlank(message = "Product name is required")
    @Size(max = 50, message = "Product cannot be more than 50 characters.")
    private String productName;

    @PositiveOrZero(message = "Total cost cannot be negative")
    private BigDecimal totalMaterialsCost = new BigDecimal(BigInteger.ZERO);;

    @PositiveOrZero(message = "Total hours to make cannot be negative")
    private int timeToMake;

    @NotBlank(message = "UserId is required")
    private String userId;

    private  ListedProduct listedProduct = new ListedProduct();

    private List<ProductMaterial> materials = new ArrayList<>();

    public boolean equals(Product product) {
        return this.productId == product.productId;
    }




}
