package megaptera.kr.jingwook.makaogift.controllers;

import megaptera.kr.jingwook.makaogift.dtos.ProductDto;
import megaptera.kr.jingwook.makaogift.dtos.ProductsDto;
import megaptera.kr.jingwook.makaogift.models.Product;
import megaptera.kr.jingwook.makaogift.models.ProductPage;
import megaptera.kr.jingwook.makaogift.services.PageService;
import megaptera.kr.jingwook.makaogift.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
public class ProductsController {
    private final ProductService productService;
    private final PageService pageService;

    public ProductsController(ProductService productService,
                              PageService pageService) {
        this.productService = productService;
        this.pageService = pageService;
    }

    @GetMapping("{id}")
    public ProductDto product(
            @PathVariable Long id
    ) {
        Product product = productService.product(id);

        return product.toDto();
    }

    @GetMapping
    public ProductsDto list(
            @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        Page<Product> products = productService.list(page);

        int pages = products.getTotalPages();

        List<ProductPage> productPages = pageService.pages(pages);

        return new ProductsDto(
                products.stream()
                .map(Product::toDto)
                .collect(Collectors.toList())
                        ,
                productPages.stream()
                .map(ProductPage::toDto)
                .collect(Collectors.toList()));
    }
}
