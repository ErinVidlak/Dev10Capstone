package learn.inventory.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {

    //TODO: Later after controllers + security have been added,
    //TODO: remove userId setter & make userId `final`
    private String userId;


    private List<Material> materials = new ArrayList<>();


    private List<Product> products = new ArrayList<>();
}
