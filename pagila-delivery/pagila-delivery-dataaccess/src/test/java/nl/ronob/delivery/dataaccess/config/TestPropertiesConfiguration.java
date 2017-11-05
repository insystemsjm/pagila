package nl.ronob.delivery.dataaccess.config;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.Ordered;

@Configuration
public class TestPropertiesConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestPropertiesConfiguration.class);

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() throws IOException {
        final PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        final Properties props = getProperties();
        propertySourcesPlaceholderConfigurer.setProperties(props);
        propertySourcesPlaceholderConfigurer.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return propertySourcesPlaceholderConfigurer;
    }

    public static Properties getProperties() throws IOException {
        final Properties props = new Properties();
        LOGGER.info("Get properties");

        //poort
        final String poort;
        try (ServerSocket databasePort = new ServerSocket(0)) {
            poort = Integer.toString(databasePort.getLocalPort());
        }
        Arrays.asList("test.database.port", "jdbc.database.port").forEach(key -> props.setProperty(key, poort));

        //url
        final String url = String.format("jdbc:hsqldb:hsql://localhost:%s/pagila", poort);
        Arrays.asList("jdbc.master.url").forEach(key -> props.setProperty(key, url));

        //driver
        Arrays.asList("jdbc.master.driverClassName").forEach(key -> props.setProperty(key, "org.hsqldb.jdbc.JDBCDriver"));

        //credentials
        props.setProperty("jdbc.master.username", "sa");
        props.setProperty("jdbc.master.password", "");

        return props;
    }
}
