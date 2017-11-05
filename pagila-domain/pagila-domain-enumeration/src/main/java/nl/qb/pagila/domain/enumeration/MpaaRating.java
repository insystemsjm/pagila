package nl.qb.pagila.domain.enumeration;

/**
 * MpaaRating enumeration..
 */
public enum MpaaRating {

    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");

    private final String code;

    MpaaRating(final String code) {
        this.code = code;
    }

    /**
     * Return {@link MpaaRating} value by parsing the corresponding code.
     * @param code the rating code
     * @return {@link MpaaRating} instance
     */
    public static MpaaRating parseCode(final String code) {
        for (final MpaaRating rating : MpaaRating.values()) {
            if (rating.getCode().equals(code)) {
                return rating;
            }
        }
        throw new IllegalArgumentException(String.format("Mpaa rating with code %s can not be parsed", code));
    }

    /**
     * Returns rating code.
     * @return textual representation of the code
     */
    public String getCode() {
        return code;
    }

}
