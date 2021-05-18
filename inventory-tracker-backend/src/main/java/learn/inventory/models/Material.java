package learn.inventory.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class Material {

    @Getter
    @Setter
    private int materialId;

    @Getter
    @Setter
    private String materialName;

    @Getter
    @Setter
    private BigDecimal pricePerUnit;


    @Getter
    @Setter
    private User user;

}
