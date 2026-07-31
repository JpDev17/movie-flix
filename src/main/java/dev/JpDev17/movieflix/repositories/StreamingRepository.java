package dev.JpDev17.movieflix.repositories;

import dev.JpDev17.movieflix.entities.Streaming;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreamingRepository extends JpaRepository<Streaming, Long> {
}
