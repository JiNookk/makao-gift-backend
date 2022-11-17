package megaptera.kr.jingwook.makaogift.exceptions;

public class ProductNotFound extends RuntimeException {
    public ProductNotFound(Long productId) {
        super("Can not find Product ID:" + productId);
    }
}
