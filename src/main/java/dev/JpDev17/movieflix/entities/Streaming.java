package dev.JpDev17.movieflix.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_streaming")
public class Streaming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    public Streaming() {
    }

    public Streaming(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Streaming{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
