package dev.JpDev17.movieflix.mapper;

import dev.JpDev17.movieflix.entities.Category;
import dev.JpDev17.movieflix.entities.Movie;
import dev.JpDev17.movieflix.entities.Streaming;
import dev.JpDev17.movieflix.request.MovieRequest;
import dev.JpDev17.movieflix.response.CategoryResponse;
import dev.JpDev17.movieflix.response.MovieResponse;
import dev.JpDev17.movieflix.response.StreamingResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieMapper {

    public static Movie toMovie(MovieRequest movieRequest) {

        List<Category> categories = movieRequest.categories().stream()
                .map(categoryId -> {
                    Category category = new Category();
                    category.setId(categoryId);
                    return category;
                })
                .toList();

        List<Streaming> streamings = movieRequest.streamings().stream()
                .map(streamingId -> {
                    Streaming streaming = new Streaming();
                    streaming.setId(streamingId);
                    return streaming;
                })
                .toList();

        Movie movie = new Movie();
        movie.setTitle(movieRequest.title());
        movie.setDescription(movieRequest.description());
        movie.setReleaseDate(movieRequest.releaseDate());
        movie.setRating(movieRequest.rating());
        movie.setCreatedAt(movieRequest.createdAt());
        movie.setUpdatedAt(movieRequest.updatedAt());
        movie.setCategories(categories);
        movie.setStreamings(streamings);
        return movie;
    }

    public static MovieResponse toMovieResponse(Movie movie) {

        List<CategoryResponse> categories = movie.getCategories().stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();

        List<StreamingResponse> streaming = movie.getStreamings().stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();

        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getReleaseDate(),
                movie.getRating(),
                movie.getCreatedAt(),
                movie.getUpdatedAt(),
                categories,
                streaming
        );
    }
}
