package vn.aptech.powerofspeed.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.powerofspeed.model.products.BidDetail;
import vn.aptech.powerofspeed.repository.product.BidDetailRepository;
import vn.aptech.powerofspeed.service.BidDetailService;

@Service
public class BidDetailImpl implements BidDetailService {

    @Autowired
    private BidDetailRepository bidDetailRepository;

    @Override
    public BidDetail stored(BidDetail bidDetail) {
        return bidDetailRepository.save(bidDetail);
    }
}
