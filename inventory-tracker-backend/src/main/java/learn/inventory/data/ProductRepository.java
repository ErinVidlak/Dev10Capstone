package learn.inventory.data;

import learn.inventory.models.Product;

import java.util.List;

public interface ProductRepository {
    Product findById(int productId);

    List<Product> findAll();

    Product add(Product product);

    boolean update(Product product);

    boolean deleteById(int productId);
}
