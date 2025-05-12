package ro.tabusca.storemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tabusca.storemanagement.entity.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByName(String name);
}