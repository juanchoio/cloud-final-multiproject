package co.com.poli.booking.entities;

import co.com.poli.booking.models.Showtime;
import co.com.poli.booking.models.User;
import co.com.poli.commons.models.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "bookings")
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @NotNull(message = "No puede ser vacío")
    @Positive(message = "No puede ser negativo")
    @Column(name = "user_id")
    private Long userId;

    @Transient
    private User user;

    @NotNull(message = "No puede ser vacío")
    @Positive(message = "No puede ser negativo")
    @Column(name = "showtime_id")
    private Long showtimeId;

    @Transient
    private Showtime showtime;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "booking_movie", joinColumns = @JoinColumn(name = "movie_id"))
    private List<Long> movies;

    @Transient
    private List<Movie> movieList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
