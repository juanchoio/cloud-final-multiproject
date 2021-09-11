package co.com.poli.movie.models;

import lombok.Data;

import java.util.List;

@Data
public class Booking {

    private Long id;
    private Long userId;
    private Long showtimeId;
    private List<Long> movies;
}
