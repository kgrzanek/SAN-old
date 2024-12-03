// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;

@QuarkusTest
class MyEntityTest {

  @SuppressWarnings("static-method")
  @Test
  @Transactional
  void testPersist() {
    MyEntity entity1 = new MyEntity();
    var fieldValue = "fieldValue-" + UUID.randomUUID();

    entity1.setField(fieldValue);
    entity1.persist();

    @SuppressWarnings("static-access")
    MyEntity entity2 = MyEntity
        .find("field = :fieldValue", Map.of("fieldValue", fieldValue))
        .firstResult();

    assertThat(entity2.getField()).isEqualTo(fieldValue);
  }

}
