package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import exercise.repository.ProductRepository;
import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ProductMapper;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @Autowired
    private ProductMapper mapper;

    @GetMapping(path = "")
    public List<ProductDTO> showAll() {
        var allModels = productRepository.findAll();
        return allModels.stream()
                .map(mapper::map)
                .toList();
    }

    @GetMapping(path = "{id}")
    public ProductDTO show(@PathVariable Long id) {
        var mbProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
        return mapper.map(mbProduct);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody ProductCreateDTO dto) {
        var model = mapper.map(dto);
        var saved = productRepository.save(model);
        return mapper.map(saved);
    }

    @PutMapping(path = "{id}")
    public void update (@PathVariable Long id,
                        @RequestBody ProductUpdateDTO dto) {
        var modelForUpdate = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
        mapper.update(dto, modelForUpdate);
        productRepository.save(modelForUpdate);

    }
    // END
}
