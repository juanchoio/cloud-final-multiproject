package co.com.poli.movie.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @NotEmpty(message = "El titulo no puede estar vacio")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "El campo director no puede estar vacio")
    @Column(name = "director")
    private String director;

    @Min(value = 1, message = "El rating no puede ser menor a 1")
    @Max(value = 5, message = "E rating no puede ser mayor a 5")
    @Column(name = "rating")
    private Integer rating;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
