package nl.ronob.delivery.dataaccess;

import java.util.List;
import javax.inject.Inject;
import nl.qb.pagila.common.test.dal.data.Data;
import nl.qb.pagila.domain.entity.dvdrental.Film;
import nl.qb.pagila.service.dal.FilmRepository;
import nl.ronob.delivery.dataaccess.config.AbstractDataAccessTest;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore //TODO public vs pagila schema fixen
@Data(resources = {"classpath:/data/dataset_film.xml"})
public class FilmJpaRepositoryImplTest extends AbstractDataAccessTest {

    @Inject
    private FilmRepository filmRepository;

    @Test
    public void getAllFilms() {
        List<Film> films = filmRepository.findAllfilms();
        Assert.assertEquals(2, films.size());

    }

    @Test
    public void testGetFilmById() {
        //film instance inserted by SQL script
        final Film film1 = filmRepository.findFilmById(1);
        Assert.assertNotNull(film1);
        Assert.assertEquals("Features", film1.getSpecialFeatures().get(0));

        //film instance inserted by xml dataset
        final Film film2 = filmRepository.findFilmById(2);
        Assert.assertNotNull(film2);
        Assert.assertNull(film2.getSpecialFeatures());
    }
}
