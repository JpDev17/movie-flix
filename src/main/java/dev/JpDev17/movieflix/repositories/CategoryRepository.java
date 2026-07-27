package dev.JpDev17.movieflix.repositories;

import dev.JpDev17.movieflix.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
