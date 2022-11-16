package megaptera.kr.jingwook.makaogift.controllers;

import megaptera.kr.jingwook.makaogift.services.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
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
    void createOrder() throws Exception {
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

        verify(orderService).createOrder(orderDto.getProductId(), orderDto.getOrderCount(), orderDto.getTotalPrice(), orderDto.getTo(), orderDto.getAddress(), any());
    }

    @Test
    void createOrderWithIncorrectTotalPrice() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"id\":1, " +
                                "\"orderCount\":1, " +
                                "\"totalPrice\":-10000, " +
                                "\"to\":\"jackson\", " +
                                "\"address\":\"New York\", " +
                                "\"message\":\"Hi\"" +
                                "}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(
                        containsString("Invalid amount")
                ));

        verify(orderService).createOrder(orderDto.getProductId(), orderDto.getOrderCount(), orderDto.getTotalPrice(), orderDto.getTo(), orderDto.getAddress(), any());
    }
}
