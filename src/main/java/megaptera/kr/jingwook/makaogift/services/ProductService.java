package megaptera.kr.jingwook.makaogift.services;

import megaptera.kr.jingwook.makaogift.exceptions.ProductNotFound;
import megaptera.kr.jingwook.makaogift.models.Product;
import megaptera.kr.jingwook.makaogift.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> list(int page) {
        Pageable pageable = PageRequest.of(page - 1, 8);

        return productRepository.findAll(pageable);
    }

    public Product product(long id) {
        return productRepository.findById(id)
                .orElseThrow(ProductNotFound::new);
    }
}
