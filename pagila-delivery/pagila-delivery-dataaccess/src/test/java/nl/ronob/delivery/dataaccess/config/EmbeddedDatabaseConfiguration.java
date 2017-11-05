package nl.ronob.delivery.dataaccess.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Configuration for embedded database.
 */
@Configuration
@Import(TestPropertiesConfiguration.class)
@ImportResource(value = {"config/test-embedded-database.xml"})
public class EmbeddedDatabaseConfiguration {
}
