// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.authentication.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ProfileEntityTest {

  @SuppressWarnings("static-method")
  @Test
  void testProfileEntitiesDetachedCreation() {
    final var profileEntity1 = new B2CEntity();
    profileEntity1.id = 1L;

    final var profileEntity2 = new B2CEntity();
    profileEntity2.id = 1L;

    final var profileEntity3 = new B2CEntity();

    assertThat(profileEntity1)
        .isNotEqualTo(profileEntity2)
        .isNotEqualTo(profileEntity3);

    assertThat(profileEntity2)
        .isNotEqualTo(profileEntity3);
  }

}
