package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN
@Component
public class ProductSpecification {

    public Specification<Product> build(ProductParamsDTO params) {
        return withTitleContaining(params.getTitleCont())
                .and(withCategoryId(params.getCategoryId()))
                .and(withPriceGreaterThan(params.getPriceGt()))
                .and(withPriceLessThan(params.getPriceLt()))
                .and(withRatingGreaterThan(params.getRatingGt()));
    }

    private Specification<Product> withTitleContaining(String titleCont) {
        return (root, query, cb) -> titleCont == null
                ? cb.conjunction()
                : cb.like(cb.lower(root.get("title")), "%" + titleCont.toLowerCase() + "%");
    }

    private Specification<Product> withCategoryId(Long categoryId) {
        return (root, query, cb) -> categoryId == null
                ? cb.conjunction()
                : cb.equal(root.get("category").get("id"), categoryId);
    }

    private Specification<Product> withPriceGreaterThan(Integer priceGt) {
        return (root, query, cb) -> priceGt == null
                ? cb.conjunction()
                : cb.greaterThan(root.get("price"), priceGt);
    }

    private Specification<Product> withPriceLessThan(Integer priceLt) {
        return (root, query, cb) -> priceLt == null
                ? cb.conjunction()
                : cb.lessThan(root.get("price"), priceLt);
    }

    private Specification<Product> withRatingGreaterThan(Double ratingGt) {
        return (root, query, cb) -> ratingGt == null
                ? cb.conjunction()
                : cb.greaterThan(root.get("rating"), ratingGt);
    }
}
// END
