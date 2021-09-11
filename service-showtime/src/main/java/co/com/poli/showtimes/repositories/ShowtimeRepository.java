package co.com.poli.showtimes.repositories;

import co.com.poli.showtimes.entities.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
}
