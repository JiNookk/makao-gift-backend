package megaptera.kr.jingwook.makaogift.dtos;

import megaptera.kr.jingwook.makaogift.models.ProductPage;

import java.util.List;

public class ProductsDto {
    private List<ProductDto> products;

    private List<ProductPageDto> productPages;

    public ProductsDto() {
    }

    public ProductsDto(List<ProductDto> products, List<ProductPageDto> productPages) {
        this.products = products;
        this.productPages = productPages;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public List<ProductPageDto> getProductPages() {
        return productPages;
    }
}
