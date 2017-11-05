package nl.qb.pagila.domain.entity.common.usertype;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;
import org.springframework.util.ObjectUtils;

public class StringListType implements UserType {

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.VARCHAR};
    }

    @Override
    public Class returnedClass() {
        return List.class;
    }

    @Override
    public boolean equals(Object o, Object o1) {
        return ObjectUtils.nullSafeEquals(o, o1);
    }

    @Override
    public int hashCode(Object o) {
        if (o != null) {
            return o.hashCode();
        } else {
            return 0;
        }
    }

    @Override
    public final Object nullSafeGet(
            final ResultSet resultSet,
            final String[] names,
            final SessionImplementor session,
            final Object owner) throws SQLException {
        if (resultSet.wasNull() || resultSet.getArray(names[0]) == null) {
            return null;
        }

        Object[] array = (Object[]) resultSet.getArray(names[0]).getArray();
        List<String> entryList = new ArrayList<>();
        for (Object o : array) {
            entryList.add((String) o);
        }
        return entryList;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SessionImplementor sessionImplementor)
            throws SQLException {
        if (o == null) {
            preparedStatement.setNull(i, Types.VARCHAR);
        } else {
            preparedStatement.setString(i, serialize((List<String>) o));
        }
    }

    @Override
    public Object deepCopy(Object o) {
        return o;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object o) {
        return (Serializable) o;
    }

    @Override
    public Object assemble(Serializable serializable, Object o) {
        return serializable;
    }

    @Override
    public Object replace(Object o, Object o1, Object o2) {
        return o;
    }

    private String serialize(List<String> list) {
        StringBuilder strbul = new StringBuilder();
        Iterator<String> iter = list.iterator();
        strbul.append("{");
        while (iter.hasNext()) {
            strbul.append(iter.next());
            if (iter.hasNext()) {
                strbul.append(",");
            }
        }
        strbul.append("}");
        return strbul.toString();
    }
}