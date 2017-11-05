package nl.qb.pagila.domain.entity.common;

import javax.persistence.MappedSuperclass;
import nl.qb.pagila.domain.entity.common.usertype.StringListType;
import org.hibernate.annotations.TypeDef;

@TypeDef(
        name = "string-arraylist",
        typeClass = StringListType.class
)
@MappedSuperclass
public class BaseEntity {
}
