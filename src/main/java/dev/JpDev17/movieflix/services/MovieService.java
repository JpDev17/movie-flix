package dev.JpDev17.movieflix.services;

import dev.JpDev17.movieflix.entities.Category;
import dev.JpDev17.movieflix.entities.Movie;
import dev.JpDev17.movieflix.entities.Streaming;
import dev.JpDev17.movieflix.mapper.MovieMapper;
import dev.JpDev17.movieflix.repositories.CategoryRepository;
import dev.JpDev17.movieflix.repositories.MovieRepository;
import dev.JpDev17.movieflix.repositories.StreamingRepository;
import dev.JpDev17.movieflix.request.MovieRequest;
import dev.JpDev17.movieflix.response.MovieResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;
    private final StreamingRepository streamingRepository;

    public MovieService(MovieRepository movieRepository, CategoryRepository categoryRepository, StreamingRepository streamingRepository) {
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
        this.streamingRepository = streamingRepository;
    }

    public List<MovieResponse> getAll() {
        List<Movie> movieList = movieRepository.findAll();
        return movieList.stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
    }

    public MovieResponse getById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movie with id " + id + " not found!"));
        return MovieMapper.toMovieResponse(movie);
    }

    public MovieResponse createMovie(MovieRequest request) {
        Movie createdMovie = MovieMapper.toMovie(request);
        setRelationships(createdMovie, request);

        Movie newMovie = movieRepository.save(createdMovie);
        return MovieMapper.toMovieResponse(newMovie);
    }

    public MovieResponse updateMovie(Long id, MovieRequest request) {
        if (!movieRepository.existsById(id)) {
            throw new EntityNotFoundException("Movie with id " + id + " not found!");
        }

        Movie createdMovie = MovieMapper.toMovie(request);
        createdMovie.setId(id);
        setRelationships(createdMovie, request);

        Movie newMovie = movieRepository.save(createdMovie);
        return MovieMapper.toMovieResponse(newMovie);
    }

    public void deleteById(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new EntityNotFoundException("Movie with id " + id + " not found!");
        }
        movieRepository.deleteById(id);
    }

    public void setRelationships(Movie movie, MovieRequest request) {
        List<Category> categories = categoryRepository.findAllById(request.categories());
        List<Streaming> streamings = streamingRepository.findAllById(request.streamings());

        if (categories.size() != request.categories().size()) {
            throw new EntityNotFoundException("One or more cateogry ids not found!");
        }

        if (streamings.size() != request.streamings().size()) {
            throw new EntityNotFoundException("One or more streamings ids not found!");
        }

        movie.setCategories(categories);
        movie.setStreamings(streamings);
    }

    public List<MovieResponse> findByCategory(Long categoryId) {
       List<Movie> movies = movieRepository.findByCategories_Id(categoryId);
       return movies.stream()
               .map(MovieMapper::toMovieResponse)
               .collect(Collectors.toList());
    }
}
