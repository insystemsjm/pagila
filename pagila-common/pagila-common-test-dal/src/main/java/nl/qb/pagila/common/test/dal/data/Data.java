package nl.qb.pagila.common.test.dal.data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class-level annotation to indicate which data should be loaded by DBUnit.
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
public @interface Data {
    /**
     * Resources (Spring notation) to be loaded.
     *
     * @return lijst van resources
     */
    String[] resources();
}
