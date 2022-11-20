package megaptera.kr.jingwook.makaogift.services;

import megaptera.kr.jingwook.makaogift.models.ProductPage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PageService {
    public List<ProductPage> pages(int page) {
        List<ProductPage> productPages = new ArrayList<>();

        for (long i = 0; i < page; i += 1) {
            long value = i + 1;
            productPages.add(new ProductPage(value));
        }

        return productPages;
    }
}
