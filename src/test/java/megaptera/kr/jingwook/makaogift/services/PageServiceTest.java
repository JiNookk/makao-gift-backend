package megaptera.kr.jingwook.makaogift.services;

import megaptera.kr.jingwook.makaogift.models.ProductPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PageServiceTest {
    PageService pageService;

    @BeforeEach
    void setup() {
        pageService = new PageService();
    }

    @Test
    void productPageWithOnePage() {
        List<ProductPage> pages = pageService.pages(1);

        assertThat(pages).hasSize(1);

        ProductPage pageOne = pages.get(0);

        assertThat(pageOne.value()).isEqualTo(1L);
    }

    @Test
    void productPagesWithMultiplePage() {
        List<ProductPage> pages = pageService.pages(2);

        assertThat(pages).hasSize(2);

        ProductPage pageOne = pages.get(0);

        assertThat(pageOne.value()).isEqualTo(1L);

        ProductPage pageTwo = pages.get(1);

        assertThat(pageTwo.value()).isEqualTo(2L);
    }

    @Test
    void productPagesWithZero() {
        List<ProductPage> pages = pageService.pages(0);

        assertThat(pages).hasSize(0);
    }

    @Test
    void productPagesWithNegativeNumber() {
        List<ProductPage> pages = pageService.pages(-1);

        assertThat(pages).hasSize(0);
    }
}
