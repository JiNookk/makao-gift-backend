package megaptera.kr.jingwook.makaogift.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {
    @Test
    void equals() {
        Product product = Product.fake("테스트용 아이디");

        assertThat(product.equals(Product.fake("테스트용 아이디"))).isTrue();
        assertThat(product.equals(null)).isFalse();
        assertThat(product.equals("테스트용 아이디")).isFalse();
    }
}
