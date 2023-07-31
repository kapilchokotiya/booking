package com.booking.controller;

import com.booking.entities.Bus;
import com.booking.entities.BusOperator;
import com.booking.entities.Route;
import com.booking.entities.Schedule;
import com.booking.sevice.BusOperatorService;
import com.booking.sevice.BusService;
import com.booking.sevice.RouteService;
import com.booking.sevice.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//date 27/4/23
@RestController
@RequestMapping("/api/BusOperator")
public class BusOperatorController {

    private final BusOperatorService busOperatorService;
    private final BusService busService;
    private final ScheduleService scheduleService;
    private final RouteService routeService;


    @Autowired
    public BusOperatorController(BusOperatorService busOperatorService, BusService
            busService, ScheduleService scheduleService, RouteService routeService) {
        this.busOperatorService = busOperatorService;
        this.busService = busService;
        this.scheduleService=scheduleService;
        this.routeService=routeService;
    }
    //http://localhost:8080/api/BusOperator/create
    @PostMapping("/create")
    public ResponseEntity<BusOperator> createBusOperator(@RequestBody BusOperator busOperator) {
        BusOperator createdBusOperator = busOperatorService.createBusOperator(busOperator);
        return new ResponseEntity<>(createdBusOperator, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/BusOperator/buses
    @PostMapping("/buses")
    public ResponseEntity<Bus> createBus(@RequestBody Bus bus) {
        Bus createdBus = busService.createBus(bus);
        return new ResponseEntity<>(createdBus,HttpStatus.CREATED) ;
    }
    //http://localhost:8080/api/BusOperator/schedules
    @PostMapping("/schedules")
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule) {
        Schedule createdSchedule = scheduleService.createSchedule(schedule);
        return  new ResponseEntity<>(createdSchedule,HttpStatus.CREATED);

    }
    //http://localhost:8080/api/BusOperator/routes
    @PostMapping("/routes")
    public ResponseEntity<Route> createRoute(@RequestBody Route route) {
        Route createdRoute = routeService.createRoute(route);
        return new ResponseEntity<>(createdRoute, HttpStatus.CREATED);
    }

}
