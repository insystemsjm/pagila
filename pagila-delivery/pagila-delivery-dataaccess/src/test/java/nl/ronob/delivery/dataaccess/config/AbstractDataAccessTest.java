package nl.ronob.delivery.dataaccess.config;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import nl.qb.pagila.common.test.dal.data.DBUnitLoaderTestExecutionListener;
import nl.qb.pagila.common.test.dal.data.DataSourceProvider;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Abstract superclass for repository (persistence) testcases.
 */
@Rollback()
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional("masterTransactionManager")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestExecutionListeners({DBUnitLoaderTestExecutionListener.class})
@ContextConfiguration(classes = AbstractDataAccessTest.DataAccessTestConfiguratie.class)
public abstract class AbstractDataAccessTest extends AbstractTransactionalJUnit4SpringContextTests implements DataSourceProvider {

    private DataSource dataSource;

    @Override
    @Inject
    @Named("masterDataSource")
    public void setDataSource(final DataSource dataSource) {
        super.setDataSource(dataSource);
        this.dataSource = dataSource;
    }

    /**
     * Implementation {@link DataSourceProvider}.
     * @return {@link DataSource}
     */
    @Override
    public final DataSource getDataSource() {
        return this.dataSource;
    }


    @Configuration
    @ComponentScan(value = {"nl.qb.pagila.delivery.dataaccess"})
    @Import(TestPropertiesConfiguration.class)
    @ImportResource(value = {"config/test-embedded-database.xml", "/config/pagila-dal-test-datasource.xml"})
    public static class DataAccessTestConfiguratie {
    }
}
