package com.booking.sevice.Impl;


import com.booking.entities.Bus;
import com.booking.repository.BusRepository;
import com.booking.sevice.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusServiceImpl implements BusService {

    private final BusRepository busRepository;

    @Autowired
    public BusServiceImpl(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @Override
    public Bus createBus(Bus bus) {
        return busRepository.save(bus);
    }
}
