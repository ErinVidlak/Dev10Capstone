package learn.inventory.data;

import learn.inventory.models.ListedProduct;

import java.util.List;

public interface ListedProductRepository {

    ListedProduct findById(int listedProductId);

    List<ListedProduct> findAll();

    ListedProduct add(ListedProduct listedProduct);

    boolean update(ListedProduct listedProduct);

    boolean deleteById(int listedProductId);
}
