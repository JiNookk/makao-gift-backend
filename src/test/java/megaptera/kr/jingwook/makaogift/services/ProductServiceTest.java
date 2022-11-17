package megaptera.kr.jingwook.makaogift.services;

import megaptera.kr.jingwook.makaogift.exceptions.ProductNotFound;
import megaptera.kr.jingwook.makaogift.models.Product;
import megaptera.kr.jingwook.makaogift.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ProductServiceTest {
    ProductService productService;
    ProductRepository productRepository;

    @BeforeEach
    void setup() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    void findProductWithExistingid() {
        Product product = Product.fake("테스트용 아이템");
        given(productRepository.findById(1L))
                .willReturn(Optional.of(product));

        Product found = productService.product(1L);

        assertThat(found.id()).isEqualTo(1L);

        verify(productRepository).findById(1L);
    }

    @Test
    void findProductWithNotExistingid() {
        given(productRepository.findById(any()))
                .willThrow(new ProductNotFound(any()));


        assertThrows(ProductNotFound.class, () -> {
            Product found = productService.product(-1L);
        });
    }

    @Test
    void listWithOneItem() {
        Product product = Product.fake("테스트용 아이템");

        Page<Product> page = new PageImpl<>(List.of(product));

        given(productRepository.findAll((Pageable) any()))
                .willReturn(page);

        Page<Product> products = productService.list(1);

        assertThat(products).hasSize(1);
        assertThat(products.getTotalPages()).isEqualTo(1);

        verify(productRepository).findAll((Pageable) any());
    }

    @Test
    void listWith30Items() {
        List<Product> productList = new ArrayList<>();

        for (int i = 0; i < 30; i += 1) {
            productList.add(Product.fake("테스트용 아이템"));
        }

        Pageable pageable = PageRequest.of(0, 8);

        Page<Product> page = new PageImpl<>(productList, pageable, 30);

        given(productRepository.findAll((Pageable) any()))
                .willReturn(page);

        Page<Product> products = productService.list(1);

        assertThat(products).hasSize(30);
        assertThat(products.getTotalPages()).isEqualTo(4);

        verify(productRepository).findAll((Pageable) any());
    }
}
