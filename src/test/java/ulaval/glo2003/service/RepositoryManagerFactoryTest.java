package ulaval.glo2003.service;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

class RepositoryManagerFactoryTest {

    @Test
    public void canCreate() {
        RepositoryManagerFactory factory = new RepositoryManagerFactory();

        RepositoryManager manager = factory.create();

        assertThat(manager).isNotNull();
    }
}