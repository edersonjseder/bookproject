package com.ecommerce.book.repository;

import com.ecommerce.book.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * Behind the scenes, this spring method is equivalent to:
     * SELECT * FROM Product p WHERE p.id = :id;
     * @param id The category id parameter from URL
     * @param pageable The pages to be shown on UI
     * @return The Product entity inside Page class
     */
    Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);

    /**
     * Behind the scenes, this spring method is equivalent to:
     * SELECT * FROM Product p WHERE p.name LIKE CONCAT('%', :name, '%');
     *
     * @param name The name parameter from URL
     * @param pageable The pages to be shown on UI
     * @return The Product entity inside Page class
     */
    Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable);
}
