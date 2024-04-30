package cholog.controller;

import cholog.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/products")
    public ResponseEntity<Void> searchProduct(@RequestParam final String keyword) {
        if (true) {
            throw new IllegalArgumentException("Invalid keyword: " + keyword);
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Void> getProduct(@PathVariable final Long id) {
        if (true) {
            throw new NotFoundException("Product not found: id=" + id);
        }

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> handIllegalArgumentException(final IllegalArgumentException exception) {
        System.err.println(exception.getMessage());
        return ResponseEntity.badRequest().build();
    }
}
