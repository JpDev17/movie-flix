package dev.JpDev17.movieflix.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    private Double rating;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(name = "movie_category",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    @ManyToMany
    @JoinTable(name = "movie_streaming",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "streaming_id"))
    private List<Streaming> streamings;

    public Movie() {
    }

    public Movie(Long id, String title, String description, LocalDate releaseDate, Double rating, LocalDateTime createdAt, LocalDateTime updatedAt, List<Category> categories, List<Streaming> streamings) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.categories = categories;
        this.streamings = streamings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Streaming> getStreamings() {
        return streamings;
    }

    public void setStreamings(List<Streaming> streamings) {
        this.streamings = streamings;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                ", rating=" + rating +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", categories=" + categories +
                ", streamings=" + streamings +
                '}';
    }
}
