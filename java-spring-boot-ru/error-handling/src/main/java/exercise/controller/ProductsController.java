package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "")
    public List<Product> index() {
        return productRepository.findAll();
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // BEGIN
    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product show(@PathVariable long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Product with id " + id + " not found."));
    }

    @PutMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product update(@RequestBody Product product, @PathVariable long id) {
        var old = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found."));
        old.setPrice(product.getPrice());
        old.setTitle(product.getTitle());

        return productRepository.save(old);
    }
    // END

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        productRepository.deleteById(id);
    }
}
