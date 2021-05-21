package learn.inventory.models;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Product {


    private int productId;

    private String productName;

    private BigDecimal totalMaterialsCost;

    private int timeToMake;

    private String userId;

    private List<ProductMaterial> materials = new ArrayList<>();

    private  ListedProduct listedProduct;


}
