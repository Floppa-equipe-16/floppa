package ulaval.glo2003.service;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

class SellingServiceFactoryTest {

    @Test
    public void canCreate() {
        SellingServiceFactory factory = new SellingServiceFactory();

        SellingService service = factory.create();

        assertThat(service).isNotNull();
    }
}
