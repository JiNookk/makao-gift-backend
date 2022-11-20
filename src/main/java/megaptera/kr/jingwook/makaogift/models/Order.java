package megaptera.kr.jingwook.makaogift.models;

import megaptera.kr.jingwook.makaogift.dtos.OrderCreatedDto;
import megaptera.kr.jingwook.makaogift.dtos.OrderDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_TABLE")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private Long orderCount;

    private Long totalPrice;

    private String receiver;

    private String address;

    private String message;

    public Order() {
    }

    public Order(Product product, Long orderCount, Long totalPrice, String receiver, String address, String message) {
        this.product = product;
        this.orderCount = orderCount;
        this.totalPrice = totalPrice;
        this.receiver = receiver;
        this.address = address;
        this.message = message;
    }


    public static Order fake(Long totalPrice) {
        Product product = Product.fake("test item");

        return new Order(product, 1L, totalPrice, "Jackson", "New York", "HI");
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass().equals(Order.class) &&
                this.id.equals(((Order) other).id);
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getAddress() {
        return address;
    }

    public String getMessage() {
        return message;
    }

    public OrderDto toDto() {
        return new OrderDto(
                id, product.imagePath(), product.manufacturer(),
                product.name().value(), receiver);
    }
}
