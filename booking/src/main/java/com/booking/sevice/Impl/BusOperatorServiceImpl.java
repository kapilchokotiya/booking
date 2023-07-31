package com.booking.sevice.Impl;


import com.booking.entities.BusOperator;
import com.booking.repository.BusOperatorRepository;
import com.booking.sevice.BusOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusOperatorServiceImpl implements BusOperatorService {
    private final BusOperatorRepository busOperatorRepository;
    @Autowired
    public BusOperatorServiceImpl(BusOperatorRepository busOperatorRepository) {
        this.busOperatorRepository = busOperatorRepository;
    }
    @Override
    public BusOperator createBusOperator(BusOperator busOperator) {
        return busOperatorRepository.save(busOperator);
    }
}
