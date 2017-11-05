package nl.qb.pagila.service.dal;

import java.util.List;
import nl.qb.pagila.domain.entity.dvdrental.Film;

/**
 * Film repository.
 */
public interface FilmRepository {

    /**
     * Get {@link Film} by id.
     * @param id id
     * @return film
     */
    Film findFilmById(int id);

    /**
     * Get all {@link Film} instances.
     * @return set of films
     */
    List<Film> findAllfilms();
}
