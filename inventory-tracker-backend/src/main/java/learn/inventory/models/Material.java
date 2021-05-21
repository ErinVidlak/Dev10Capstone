package learn.inventory.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Material {


    private int materialId;

    private String materialName;

    //TODO: price per unit is a generated value
    private BigDecimal pricePerUnit;

    //user ID will always remain constant
    private String userId;

    private MaterialInventory inventory = new MaterialInventory();

    private List<MaterialPurchase> purchases = new ArrayList<>();

    private List<MaterialProduct> products = new ArrayList<>();




}
