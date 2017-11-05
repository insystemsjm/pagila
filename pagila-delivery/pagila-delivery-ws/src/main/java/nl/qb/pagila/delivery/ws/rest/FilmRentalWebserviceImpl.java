package nl.qb.pagila.delivery.ws.rest;

import nl.qb.pagila.domain.dto.FilmInfo;
import nl.qb.pagila.service.filmrental.FilmInfoService;
import org.springframework.stereotype.Service;

@Service("filmRentalService")
public class FilmRentalWebserviceImpl implements FilmRentalWebservice {

    private FilmInfoService filmInfoService;

    public FilmRentalWebserviceImpl(FilmInfoService filmInfoService) {
        this.filmInfoService = filmInfoService;
    }

    /**
     * Get filminfo in JSON format.
     * @param filmId film id (regex validated)
     * @return film info
     */
    @Override
    public FilmInfo getFilmInfo(int filmId) {
        return filmInfoService.getFilmInfo(filmId);
    }
}
