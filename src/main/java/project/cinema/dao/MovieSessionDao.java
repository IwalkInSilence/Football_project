package project.cinema.dao;

import java.time.LocalDate;
import java.util.List;
import project.cinema.model.MovieSession;

public interface MovieSessionDao {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession session);
}