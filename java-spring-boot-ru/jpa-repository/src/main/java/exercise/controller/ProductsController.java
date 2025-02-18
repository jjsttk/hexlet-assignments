package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @GetMapping("")
    public List<Product> showAllByPrice(@RequestParam(defaultValue = "0") int min,
                                        @RequestParam(defaultValue = "0") int max) {
        if (min < 0 || max < 0) {
            throw new IllegalArgumentException("Price is less than 0, bad");
        }

        int maximum = (max == 0) ? Integer.MAX_VALUE : max;
        return productRepository.findAllByPriceBetweenOrderByPriceAsc(min, maximum);
    }
    // END

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {

        var product =  productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        return product;
    }
}
