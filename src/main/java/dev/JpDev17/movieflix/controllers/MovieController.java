package dev.JpDev17.movieflix.controllers;

import dev.JpDev17.movieflix.mapper.MovieMapper;
import dev.JpDev17.movieflix.request.MovieRequest;
import dev.JpDev17.movieflix.response.MovieResponse;
import dev.JpDev17.movieflix.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        List<MovieResponse> moviesList = movieService.getAll();
        return ResponseEntity.ok(moviesList);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MovieResponse> getByMovieId(@PathVariable Long id) {
        MovieResponse movie = movieService.getById(id);
        return ResponseEntity.ok(movie);
    }

    @PostMapping("/search")
    public ResponseEntity<MovieResponse> saveMovie(@RequestBody MovieRequest request) {
        MovieResponse newMovie = movieService.createMovie(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMovie);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MovieResponse> updateByMovieId(@PathVariable Long id, @RequestBody MovieRequest request) {
        MovieResponse updatedMovie = movieService.updateMovie(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedMovie);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteByMovieId(@PathVariable Long id) {
        movieService.deleteById(id);
        return ResponseEntity.ok("Movie with id " + id + " deleted successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long categoryId) {
        return ResponseEntity.ok(movieService.findByCategory(categoryId));
    }
}
