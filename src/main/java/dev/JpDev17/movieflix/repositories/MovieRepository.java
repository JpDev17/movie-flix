package dev.JpDev17.movieflix.repositories;

import dev.JpDev17.movieflix.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByCategories_Id(Long categoryId);
}
