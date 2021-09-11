package co.com.poli.showtimes.services;

import co.com.poli.showtimes.entities.Showtime;

import java.util.List;

public interface ShowtimeService {

    Showtime findById(Long id);
    List<Showtime> findAll();
    void save(Showtime showtime);
    //void delete(Showtime showtime);
}
