package learn.inventory.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class User {

    //TODO: Later after controllers + security have been added,
    //TODO: remove userId setter, make userId `final`, and have it be a constructor arg
    @NotBlank
    @Size(max = 255, message = "UserId cannot be more than 255 characters.")
    private String userId;

    private List<Material> materials = new ArrayList<>();


    private List<Product> products = new ArrayList<>();
}
