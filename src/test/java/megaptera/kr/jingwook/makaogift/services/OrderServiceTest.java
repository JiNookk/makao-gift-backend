package megaptera.kr.jingwook.makaogift.services;

import megaptera.kr.jingwook.makaogift.exceptions.ProductNotFound;
import megaptera.kr.jingwook.makaogift.models.Order;
import megaptera.kr.jingwook.makaogift.models.Product;
import megaptera.kr.jingwook.makaogift.repositories.OrderRepository;
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

class OrderServiceTest {
    OrderService orderService;
    ProductRepository productRepository;
    OrderRepository orderRepository;

    @BeforeEach
    void setup() {
        productRepository = mock(ProductRepository.class);
        orderRepository = mock(OrderRepository.class);
        orderService = new OrderService(productRepository, orderRepository);
    }

    @Test
    void list() {
        List<Order> orders = new ArrayList<>();
        Pageable pageable = PageRequest.of(1, 8);
        int total = 30;

        for (int i = 0; i < total; i += 1) {
            orders.add(Order.fake(10000L));
        }

        Page<Order> page = new PageImpl(orders, pageable, total);
        given(orderRepository.findAll((Pageable) any()))
                .willReturn(page);

        Page<Order> pages = orderService.list(1);

        assertThat(pages).hasSize(30);
        assertThat(pages.getTotalPages()).isEqualTo(4);
    }

    @Test
    void createOrder() {
        Product product = Product.fake("test item");

        given(productRepository.findById(any()))
                .willReturn(Optional.of(product));

        String status = orderService.createOrder(
                1L, 1L, 10000L, "jackson", "new york", "hi");

        assertThat(status).isEqualTo("Order Created!");

        verify(productRepository).findById(1L);
        verify(orderRepository).save(any());
    }

    @Test
    void createOrderWithIncorrectProductId() {
        given(productRepository.findById(any()))
                .willThrow(new ProductNotFound(-1L));

        assertThrows(ProductNotFound.class, () -> {
            String status = orderService.createOrder(
                    -1L, 1L, 10000L, "jackson", "new york", "hi");
        });
    }
}
