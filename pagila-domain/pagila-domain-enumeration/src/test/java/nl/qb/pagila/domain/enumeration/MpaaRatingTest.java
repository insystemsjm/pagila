package nl.qb.pagila.domain.enumeration;


import org.junit.Assert;
import org.junit.Test;

public class MpaaRatingTest {

    @Test
    public void testCodeParsing() {
        final MpaaRating rating = MpaaRating.NC_17;
        Assert.assertEquals(rating, MpaaRating.parseCode("NC-17"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCodeParsingError() {
        MpaaRating.parseCode("NC_17");
    }

    @Test
    public void testCode() {
        Assert.assertEquals("NC-17", MpaaRating.NC_17.getCode());
    }

}