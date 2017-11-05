package nl.qb.pagila.domain.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import nl.qb.pagila.domain.entity.dvdrental.Film;

@XmlRootElement
public class FilmInfo {

    private int id;
    private String title;
    private String description;
    private Integer releaseYear;
    private String length;
    private String mpaaRating;
    private boolean rentable;

    public static FilmInfo fromFilm(final Film film) {
        final FilmInfo filmInfo = new FilmInfo();
        filmInfo.setId(film.getId());
        filmInfo.setDescription(film.getDescription());
        filmInfo.setReleaseYear(film.getReleaseYear());
        filmInfo.setLength(String.format("%d min", film.getLength()));
        filmInfo.setMpaaRating(film.getMpaaRating().getCode());
        filmInfo.setTitle(film.getTitle());
        filmInfo.setRentable(true);
        return filmInfo;
    }

    @XmlElement
    public int getId() {
        return id;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    @XmlElement
    public Integer getReleaseYear() {
        return releaseYear;
    }

    @XmlElement
    public String getLength() {
        return length;
    }

    @XmlElement
    public String getMpaaRating() {
        return mpaaRating;
    }

    @XmlElement
    public String getTitle() {
        return title;
    }

    @XmlElement
    public boolean isRentable() {
        return rentable;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public void setRentable(boolean rentable) {
        this.rentable = rentable;
    }

}
