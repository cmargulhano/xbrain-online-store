package br.com.koradi.repository;

import br.com.koradi.model.product.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, String> {
 
    List<Product> findAllByPrice(double price, Pageable pageable);
}