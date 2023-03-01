package ulaval.glo2003.service;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

class SellingServiceFactoryTest {

    @Test
    public void canCreate() {
        SellingServiceFactory factory = new SellingServiceFactory();

        SellingService manager = factory.create();

        assertThat(manager).isNotNull();
    }
}