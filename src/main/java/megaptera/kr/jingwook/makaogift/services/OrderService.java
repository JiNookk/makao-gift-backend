package megaptera.kr.jingwook.makaogift.services;

import megaptera.kr.jingwook.makaogift.exceptions.ProductNotFound;
import megaptera.kr.jingwook.makaogift.models.Order;
import megaptera.kr.jingwook.makaogift.models.Product;
import megaptera.kr.jingwook.makaogift.repositories.OrderRepository;
import megaptera.kr.jingwook.makaogift.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public Page<Order> list(int page) {
        // TODO: UserName으로 찾으려면 로그인이 되어야 한다.
        // 일단 없이 구현하고 나중에 전체를 리팩토링하는게 낫나?
        Pageable pageable = PageRequest.of(page - 1, 8);

        return orderRepository.findAll(pageable);
    }

    public String createOrder(
            Long productId, Long orderCount, Long totalPrice,
            String to, String address, String message) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFound(productId));

        Order order = new Order(product, orderCount, totalPrice, to, address, message);

        orderRepository.save(order);

        return "Order Created!";
    }
}
