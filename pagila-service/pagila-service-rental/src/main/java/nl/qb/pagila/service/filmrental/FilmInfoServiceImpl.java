package nl.qb.pagila.service.filmrental;

import javax.inject.Inject;
import nl.qb.pagila.domain.dto.FilmInfo;
import nl.qb.pagila.domain.entity.dvdrental.Film;
import nl.qb.pagila.service.dal.FilmRepository;
import org.springframework.stereotype.Service;

@Service
public class FilmInfoServiceImpl implements FilmInfoService {

    private FilmRepository filmRepository;

    @Inject
    public FilmInfoServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public FilmInfo getFilmInfo(final int filmId) {
        Film film = filmRepository.findFilmById(filmId);
        return FilmInfo.fromFilm(film);
    }
}
