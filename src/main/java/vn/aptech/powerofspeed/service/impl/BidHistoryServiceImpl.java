package vn.aptech.powerofspeed.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.powerofspeed.model.products.BidDetail;
import vn.aptech.powerofspeed.model.products.BidHistory;
import vn.aptech.powerofspeed.repository.BidHistoryRepository;
import vn.aptech.powerofspeed.service.BidDetailService;
import vn.aptech.powerofspeed.service.BidHistoryService;

import java.util.List;

@Service
public class BidHistoryServiceImpl implements BidHistoryService {

    @Autowired
    private BidHistoryRepository bidHistoryRepository;


    @Override
    public List<BidHistory> findAll() {
        return bidHistoryRepository.findAll();
    }
}
