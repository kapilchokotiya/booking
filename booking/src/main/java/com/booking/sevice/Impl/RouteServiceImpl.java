package com.booking.sevice.Impl;

import com.booking.entities.Route;
import com.booking.repository.RouteRepository;
import com.booking.sevice.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }
}

