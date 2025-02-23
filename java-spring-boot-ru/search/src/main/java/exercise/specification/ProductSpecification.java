package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN
@Component
public class ProductSpecification {
    public Specification<Product> build(ProductParamsDTO params) {
        return withTitleContains(params.getTitleCont())
                .and(withCategoryIdEquals(params.getCategoryId()))
                .and(withPriceLowerThan(params.getPriceLt()))
                .and(withPriceGreaterThan(params.getPriceGt()))
                .and(withRatingGreaterThan(params.getRatingGt()));
    }

    private Specification<Product> withTitleContains(String param) {
        return ((root, query, criteriaBuilder) -> param == null ?
                criteriaBuilder.conjunction() :
                criteriaBuilder.equal(root.get("title"), param));
    }

    private Specification<Product> withCategoryIdEquals(Long param) {
        return ((root, query, criteriaBuilder) -> param == null ?
                criteriaBuilder.conjunction() :
                criteriaBuilder.equal(root.get("category").get("id"), param));
    }

    private Specification<Product> withPriceLowerThan(Integer param) {
        return ((root, query, criteriaBuilder) -> param == null ?
                criteriaBuilder.conjunction() :
                criteriaBuilder.lessThan(root.get("price"), param));
    }

    private Specification<Product> withPriceGreaterThan(Integer param) {
        return ((root, query, criteriaBuilder) -> param == null ?
                criteriaBuilder.conjunction() :
                criteriaBuilder.greaterThan(root.get("price"), param));
    }

    private Specification<Product> withRatingGreaterThan(Double param) {
        return ((root, query, criteriaBuilder) -> param == null ?
                criteriaBuilder.conjunction() :
                criteriaBuilder.greaterThan(root.get("rating"), param));
    }

    //private String titleCont;
    //    private Long categoryId;
    //    private Integer priceLt;
    //    private Integer priceGt;
    //    private Double ratingGt;
}
// END
