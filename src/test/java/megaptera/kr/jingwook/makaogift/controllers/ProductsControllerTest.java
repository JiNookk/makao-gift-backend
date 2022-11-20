package megaptera.kr.jingwook.makaogift.controllers;

import megaptera.kr.jingwook.makaogift.models.Product;
import megaptera.kr.jingwook.makaogift.services.PageService;
import megaptera.kr.jingwook.makaogift.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductsController.class)
class ProductsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private PageService pageService;

    @Test
    void product() throws Exception {
        Product product = Product.fake("item for test");
        given(productService.product(1L))
                .willReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"name\":\"item for test\"")
                ));

        verify(productService).product(1);
    }

    @Test
    void list() throws Exception {
        Product product = Product.fake("테스트용 아이템");

        Page<Product> page = new PageImpl<>(List.of(product));
        given(productService.list(1))
                .willReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.get("/products?page=1"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"products\":[")
                ))
                .andExpect(content().string(
                        containsString("\"productPages\":[")
                ));

        verify(productService).list(1);
        verify(pageService).pages(1);
    }
}
