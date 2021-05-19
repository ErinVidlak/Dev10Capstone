package learn.inventory.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class User {

    @Getter
    @Setter
    private String userId;

    @Getter
    @Setter
    private List<Material> materials = new ArrayList<>();

    @Getter
    @Setter
    private List<Product> products = new ArrayList<>();
}
