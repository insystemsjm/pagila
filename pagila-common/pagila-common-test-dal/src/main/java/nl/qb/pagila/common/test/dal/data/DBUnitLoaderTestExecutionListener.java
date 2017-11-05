package nl.qb.pagila.common.test.dal.data;

import java.io.IOException;
import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.DatabaseUnitException;
import org.dbunit.IDatabaseTester;
import org.dbunit.IOperationListener;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * TestExecutionListener die voor het uitvoeren van testmethodes eerst DBUnit data in een
 * database inlaadt. Dit gebeurt *BUITEN* een transactie, met een
 * {@link DatabaseOperation#CLEAN_INSERT}, dus de data blijft in de database.
 * De gebruikte {@link DataSource} wordt van de test instantie gehaald.
 */
@Component
public class DBUnitLoaderTestExecutionListener extends AbstractTestExecutionListener {

    private static final String DBUNIT_INIT_ERROR_SG = "Error during initialisation of DBunit.";
    private static final Logger LOGGER = LoggerFactory.getLogger(DBUnitLoaderTestExecutionListener.class);

    //values are assigned in prepareTestInstance method
    private Class<?> currentClass;
    private IDatabaseTester databaseTester;

    @Override
    public void afterTestClass(final TestContext testContext) throws Exception {
        LOGGER.debug("teardown DBUnit databaseTester");
        if (databaseTester != null) {
            databaseTester.onTearDown();
        }
    }

    @Override
    public void prepareTestInstance(final TestContext testContext) {
        if (!testContext.getTestClass().equals(currentClass)) {
            LOGGER.debug("setUp DBUnit dataSource");
            DataSource dataSource = retrieveDataSource(testContext);

            LOGGER.debug("setUp DBUnit databaseTester");
            final ResourceLoader resourceLoader = testContext.getApplicationContext();
            try {
                final Data data = testContext.getTestClass().getAnnotation(Data.class);
                if (data != null && dataSource != null) {
                    databaseTester = new DataSourceDatabaseTester(dataSource);
                    databaseTester.setOperationListener(new MyOperationListener());
                    databaseTester.setSetUpOperation(DatabaseOperation.INSERT);

                    final CompositeDataSet dataSet = retrieveDataSets(data, resourceLoader);
                    final ReplacementDataSet filteredDataSet = new ReplacementDataSet(dataSet);
                    filteredDataSet.addReplacementObject("[NULL]", null);

                    databaseTester.setDataSet(filteredDataSet);
                    databaseTester.onSetup();
                }
            } catch (final BatchUpdateException e) {
                LOGGER.error(DBUNIT_INIT_ERROR_SG, e.getNextException());
            } catch (final DatabaseUnitException e) {
                LOGGER.error(DBUNIT_INIT_ERROR_SG, e);
                if (e.getCause() instanceof BatchUpdateException) {
                    LOGGER.error(DBUNIT_INIT_ERROR_SG, ((BatchUpdateException) e.getCause()).getNextException());
                }
            } catch (final Exception e) {
                LOGGER.error(DBUNIT_INIT_ERROR_SG, e);
            } finally {
                currentClass = testContext.getTestClass();
            }
        }
    }

    private DataSource retrieveDataSource(final TestContext testContext) {
        final Object testInstance = testContext.getTestInstance();
        if (testInstance instanceof DataSourceProvider) {
            return ((DataSourceProvider) testInstance).getDataSource();
        }
        return null;
    }

    private CompositeDataSet retrieveDataSets(final Data data, final ResourceLoader resourceLoader) throws IOException, DataSetException {
        final FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);
        final String[] dataSetFiles = data.resources();
        final List<IDataSet> dataSets = new ArrayList<>(dataSetFiles.length);
        for (final String dataSetFile : dataSetFiles) {
            final IDataSet ds;
            if (dataSetFile.endsWith(".xml")) {
                ds = builder.build(resourceLoader.getResource(dataSetFile).getInputStream());
            } else {
                throw new IllegalStateException(
                        "Only flat XML data sets are supported for the moment");
            }
            dataSets.add(ds);
        }
        return new CompositeDataSet(dataSets.toArray(new IDataSet[dataSets.size()]));
    }

    /**
     * IOperationListener implementation - sets additional configuration properties.
     */
    private static final class MyOperationListener implements IOperationListener {

        @Override
        public void connectionRetrieved(final IDatabaseConnection connection) {
            final DatabaseConfig config = connection.getConfig();
            config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new PostgresqlDataTypeFactory());
            config.setProperty(DatabaseConfig.FEATURE_QUALIFIED_TABLE_NAMES, true);
            config.setProperty(DatabaseConfig.PROPERTY_BATCH_SIZE, 300);
            config.setProperty(DatabaseConfig.FEATURE_BATCHED_STATEMENTS, true);
        }

        @Override
        public void operationSetUpFinished(final IDatabaseConnection connection) {
            //not implemented
        }

        @Override
        public void operationTearDownFinished(final IDatabaseConnection connection) {
            //not implemented
        }
    }

}
