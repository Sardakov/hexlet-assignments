package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import exercise.model.Product;

import org.springframework.data.domain.Sort;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // BEGIN
    List<Product> findByPriceBetweenOrderByPriceAsc(int minPrice, int maxPrice);

    List<Product> findByPriceGreaterThanEqualOrderByPriceAsc(int minPrice);

    List<Product> findByPriceLessThanEqualOrderByPriceAsc(int maxPrice);
    // END
}
