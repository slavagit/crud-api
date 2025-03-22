package ru.testtasks.crudapi;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.testtasks.crudapi.configuration.config.DockerConfig;
import ru.testtasks.crudapi.services.TransferService;
import ru.testtasks.crudapi.services.UserService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {CrudApiApplication.class, DockerConfig.class}, initializers = {
    AbstractCommonContext.Initializer.class})
@Slf4j
@ExtendWith({SpringExtension.class})
public abstract class AbstractCommonContext {

  @Autowired
  protected TransferService transferService;
  @Autowired
  protected UserService userService;



  static class Initializer
      implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      TestPropertyValues.of(
//          "fgis.http.host=http://localhost:" + FGISMockServer.PORT + ""
      ).applyTo(configurableApplicationContext.getEnvironment());
    }
  }
}
