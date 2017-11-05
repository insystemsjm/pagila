package nl.qb.pagila.domain.entity.dvdrental;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import nl.qb.pagila.domain.entity.common.BaseEntity;
import nl.qb.pagila.domain.enumeration.MpaaRating;
import org.hibernate.annotations.Type;

/**
 * Film entity.
 */
@Entity
@Table(name = "film", schema = "public")
public class Film extends BaseEntity {

    @Id
    @SequenceGenerator(name = "film_id_generator", sequenceName = "film_film_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "film_id_generator")
    @Column(name = "film_id", updatable = false)
    private int id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "release_year")
    private Integer releaseYear;
    @Column(name = "language_id", nullable = false)
    private short language;
    @Column(name = "rental_duration", nullable = false)
    private short rentalDuration;
    @Column(name = "rental_rate", nullable = false, precision = 4, scale = 2)
    private BigDecimal rentalRate;
    @Column(name = "length")
    private Short length;
    @Column(name = "replacement_cost", nullable = false, precision = 5, scale = 2)
    private BigDecimal replacementCost;
    @Column(name = "rating")
    private String mpaaRating;
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;

    @Type(type = "string-arraylist")
    @Column(name = "special_features")
    private List<String> specialFeatures;

    //TODO fulltext attribuut : tsvector type


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public short getLanguage() {
        return language;
    }

    public void setLanguage(short language) {
        this.language = language;
    }

    public short getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(short rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    public Short getLength() {
        return length;
    }

    public void setLength(Short length) {
        this.length = length;
    }

    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    public MpaaRating getMpaaRating() {
        return MpaaRating.parseCode(mpaaRating);
    }

    public void setMpaaRating(MpaaRating mpaaRating) {
        this.mpaaRating = mpaaRating.getCode();
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<String> getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(List<String> specialFeatures) {
        this.specialFeatures = specialFeatures;
    }
}
