package learn.inventory.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {


    private String userId;


    private List<Material> materials = new ArrayList<>();


    private List<Product> products = new ArrayList<>();
}
