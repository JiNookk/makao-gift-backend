package megaptera.kr.jingwook.makaogift.controllers;

import megaptera.kr.jingwook.makaogift.exceptions.ProductNotFound;
import megaptera.kr.jingwook.makaogift.models.Order;
import megaptera.kr.jingwook.makaogift.services.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void list() throws Exception {
        Order order = Order.fake(10000L);
        Pageable pageable = PageRequest.of(1, 8);
        int total = 30;

        Page<Order> page = new PageImpl(List.of(order), pageable, total);
        given(orderService.list(1))
                .willReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.get("/orders"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"orders\":[")
                ));

        verify(orderService).list(1);
    }

    @Test
    void listWithPage2() throws Exception {
        List<Order> orders = new ArrayList<>();
        Order order = Order.fake(10000L);
        Pageable pageable = PageRequest.of(1, 8);
        int total = 30;

        for (int i = 0; i < 10; i += 1) {
            orders.add(order);
        }

        Page<Order> page = new PageImpl(orders, pageable, total);

        given(orderService.list(2))
                .willReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.get("/orders?page=2"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"receiver\":\"Jackson\"")
                ));

        verify(orderService).list(2);
    }

    @Test
    void createOrder() throws Exception {
        given(orderService.createOrder(any(), any(), any(), any(), any(), any()))
                .willReturn("Order Created!");

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"productId\":1, " +
                                "\"orderCount\":1, " +
                                "\"totalPrice\":10000, " +
                                "\"to\":\"jackson\", " +
                                "\"address\":\"New York\", " +
                                "\"message\":\"Hi\"" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("Order Created!")
                ));

        verify(orderService).createOrder(any(), any(), any(), any(), any(), any());
    }

    @Test
    void createOrderWithIncorrectProductId() throws Exception {
        given(orderService.createOrder(any(), any(), any(), any(), any(), any()))
                .willThrow(new ProductNotFound(-1L));

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"id\":-1, " +
                                "\"orderCount\":1, " +
                                "\"totalPrice\":-10000, " +
                                "\"to\":\"jackson\", " +
                                "\"address\":\"New York\", " +
                                "\"message\":\"Hi\"" +
                                "}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(
                        containsString("Can not find Product ID:-1")
                ));

        verify(orderService).createOrder(any(), any(), any(), any(), any(), any());
    }
}
