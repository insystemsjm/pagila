package nl.qb.pagila.delivery.dataaccess.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import nl.qb.pagila.domain.entity.dvdrental.Film;
import nl.qb.pagila.service.dal.FilmRepository;
import org.springframework.stereotype.Repository;

/**
 * Filmrepository - JPA implementatie.
 */
@Repository
public class FilmJpaRepositoryImpl implements FilmRepository {

    @PersistenceContext(unitName = "nl.ronob.pagila.master")
    private EntityManager entityManager;

    @Override
    public Film findFilmById(int id) {
        if (entityManager == null) {
            throw new IllegalStateException("Entitymanager is niet geinitialiseerd.");
        }
        final String query = "select f from Film f where f.id = :filmId";
        return entityManager.createQuery(query, Film.class).setParameter("filmId", id).getSingleResult();
    }

    @Override
    public List<Film> findAllfilms() {
        final TypedQuery<Film> typedQuery =
                entityManager.createQuery("SELECT film FROM Film film", Film.class);
        return typedQuery.getResultList();
    }
}
