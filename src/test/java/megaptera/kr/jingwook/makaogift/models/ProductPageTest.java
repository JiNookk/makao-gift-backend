package megaptera.kr.jingwook.makaogift.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductPageTest {
    @Test
    void equals() {
        ProductPage productPage = new ProductPage(1L);

        assertThat(productPage.value()).isEqualTo(1L);
        assertThat(productPage.equals(new ProductPage(1L))).isTrue();
        assertThat(productPage.equals(null)).isFalse();
        assertThat(productPage.equals("1L")).isFalse();
    }
}
