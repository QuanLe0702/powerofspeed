package vn.aptech.powerofspeed.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.powerofspeed.model.products.BidHistory;
import vn.aptech.powerofspeed.model.products.Product;
import vn.aptech.powerofspeed.repository.BidHistoryRepository;
import vn.aptech.powerofspeed.repository.product.ProductRepository;
import vn.aptech.powerofspeed.service.ProductService;
import vn.aptech.powerofspeed.util.RandomStringUtil;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    private BidHistoryRepository bidHistoryRepository;

    @Override
    public List<Product> findAllPro() {
        List<Product> result = new ArrayList<>();
        Iterable<Product> listPro = productRepository.findAll();
        for (Product pro : listPro) {

            result.add(pro);
        }
        return result;
    }

    @Override
    public Product create(Product pro) {

        Product storedProduct = productRepository.save(pro);
        String makeSlug = RandomStringUtil.makeSlug(storedProduct.getProductName() + "-" + storedProduct.getId());
        storedProduct.setSlug(makeSlug);
        return productRepository.save(storedProduct);
    }

    @Override
    @Transactional
    public Product update(Product pro) {
        return productRepository.save(pro);
    }

    @Override
    public Product findPk(Long id) {
        try {
            Product product = productRepository.findById(id).get();

            return product;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product findBySlug(String productSlug) {
        return productRepository.findBySlug(productSlug);
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
    }

    @Override
    public Product bidAuction(Product product) {
        return productRepository.save(product);
    }

    @Override
    public BidHistory storeBidHistory(BidHistory bidHistory) {
        return bidHistoryRepository.save(bidHistory);
    }
}
