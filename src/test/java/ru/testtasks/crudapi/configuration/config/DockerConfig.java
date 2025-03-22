package ru.testtasks.crudapi.configuration.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.sql.DataSource;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration
public class DockerConfig {

  @Bean(initMethod = "start", destroyMethod = "stop")
  public PostgreSQLContainer jdbcDatabaseContainer() {
    return (PostgreSQLContainer) new PostgreSQLContainer(
        "postgres:11.1")
        .withDatabaseName("integration-tests-db")
        .withUsername("sa")
        .withPassword("sa")
        .withNetworkAliases("localhost")
        .withStartupTimeout(Duration.ofSeconds(720));
  }

//  @DynamicPropertySource
//  static void configureProperties(DynamicPropertyRegistry registry, JdbcDatabaseContainer<?> jdbcDatabaseContainer) {
//    registry.add("spring.datasource.url", jdbcDatabaseContainer::getJdbcUrl);
//    registry.add("spring.datasource.username", jdbcDatabaseContainer::getUsername);
//    registry.add("spring.datasource.password", jdbcDatabaseContainer::getPassword);
//  }

  @Bean
  @Primary
  public DataSource dataSource(JdbcDatabaseContainer<?> jdbcDatabaseContainer)
      throws MalformedURLException {

    final String regex = "jdbc:postgresql:\\/\\/(.*):";
    final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);

    System.out.println("=====" + jdbcDatabaseContainer.getJdbcUrl());

    final Matcher matcher = pattern.matcher(jdbcDatabaseContainer.getJdbcUrl());
    matcher.find();
    var host = matcher.group(1);

    var hikariConfig = new HikariConfig();
    hikariConfig.setJdbcUrl(jdbcDatabaseContainer.getJdbcUrl());
    hikariConfig.setUsername(jdbcDatabaseContainer.getUsername());
    hikariConfig.setPassword(jdbcDatabaseContainer.getPassword());
    hikariConfig.setMaximumPoolSize(100);

    return new HikariDataSource(hikariConfig);
  }


}
