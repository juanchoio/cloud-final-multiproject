package co.com.poli.user.models;

import lombok.*;

import java.util.List;

@Data
public class Booking {

    private Long id;
    private Long userId;
    private Long showtimeId;
    private List<Long> movies;
}
