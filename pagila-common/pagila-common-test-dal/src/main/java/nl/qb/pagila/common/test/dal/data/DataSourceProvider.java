package nl.qb.pagila.common.test.dal.data;

import javax.sql.DataSource;

/**
 * Tagging interface : tells instances about the datasource to be used.
 */
public interface DataSourceProvider {
    /**
     * Geeft een DataSource.
     * @return datasource instantie
     */
    DataSource getDataSource();
}
