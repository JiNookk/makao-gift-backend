package megaptera.kr.jingwook.makaogift.controllers;

import megaptera.kr.jingwook.makaogift.dtos.OrderDto;
import megaptera.kr.jingwook.makaogift.dtos.OrderResultDto;
import megaptera.kr.jingwook.makaogift.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResultDto createOrder(
            @RequestBody OrderDto orderDto
    ) {

        String status = orderService.createOrder(
                orderDto.getProductId(), orderDto.getOrderCount(),orderDto.getTotalPrice(),
            orderDto.getTo(),orderDto.getAddress(),orderDto.getMessage());

        return new OrderResultDto(status);
    }
}
