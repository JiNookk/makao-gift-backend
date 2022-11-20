package megaptera.kr.jingwook.makaogift.controllers;

import megaptera.kr.jingwook.makaogift.dtos.OrderCreatedDto;
import megaptera.kr.jingwook.makaogift.dtos.OrderResultDto;
import megaptera.kr.jingwook.makaogift.dtos.OrdersDto;
import megaptera.kr.jingwook.makaogift.exceptions.ProductNotFound;
import megaptera.kr.jingwook.makaogift.models.Order;
import megaptera.kr.jingwook.makaogift.services.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public OrdersDto list(
            @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        //TODO: 나중에 유저이름 or ID를 암호화한 토큰을 받아서 해당하는 주문만 가져와야 한다.

        Page<Order> orders = orderService.list(page);

        return new OrdersDto(orders.stream()
                .map(Order::toDto)
                .collect(Collectors.toList()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResultDto createOrder(
            @RequestBody OrderCreatedDto orderCreatedDto
    ) {
        String status = orderService.createOrder(
                orderCreatedDto.getId(), orderCreatedDto.getOrderCount(), orderCreatedDto.getTotalPrice(),
            orderCreatedDto.getTo(), orderCreatedDto.getAddress(), orderCreatedDto.getMessage());

        return new OrderResultDto(status);
    }

    @ExceptionHandler(ProductNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String productNotFound(ProductNotFound productNotFound) {
        return productNotFound.getMessage();
    }
}
