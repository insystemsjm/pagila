package nl.qb.pagila.service.filmrental;

import nl.qb.pagila.domain.dto.FilmInfo;

/**
 * Service to retrieve film info.
 */
public interface FilmInfoService {

    FilmInfo getFilmInfo(int filmId);
}
