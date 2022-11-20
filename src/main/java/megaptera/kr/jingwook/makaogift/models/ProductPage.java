package megaptera.kr.jingwook.makaogift.models;

import megaptera.kr.jingwook.makaogift.dtos.ProductPageDto;

import java.util.Objects;

public class ProductPage {
    private Long value;

    public ProductPage(Long value) {

        this.value = value;
    }

    public Long value() {
        return value;
    }

    public ProductPageDto toDto() {
        return new ProductPageDto(value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass().equals(ProductPage.class) &&
                Objects.equals(this.value, ((ProductPage) other).value);
    }
}
