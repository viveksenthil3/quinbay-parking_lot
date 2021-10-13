package com.travel.controller;

import com.travel.entity.Car;
import com.travel.entity.Ticket;
import com.travel.service.TicketService;
import com.travel.so.CarSo;
import com.travel.so.TicketSo;
import io.reactivex.Single;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
//import rx.Single;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class TicketController {

    @Value("${parking.id}")
    private String PARKING_ID;

    @Autowired
    TicketService ticketService;

    @PostConstruct
    public void init () {
        int i=0;
        i++;
    }

    @PostMapping("/parkCar")
    public Single<TicketSo> parkCar(@RequestBody Car car){
        CarSo newCar = new CarSo();
        BeanUtils.copyProperties(car, newCar);
        return ticketService.parkCar(newCar);
    }

    @GetMapping("/byColor/{color}")
    public Single<List<TicketSo>> byColor(@PathVariable("color") String color){
        return ticketService.getByColor(color);
    }

    @GetMapping("/byRegisterNo/{registerNo}")
    public Single<TicketSo> byRegisterNo(@PathVariable("registerNo") String registerNo){
        return ticketService.getByRegisterNo(registerNo);
    }

    @GetMapping("/leaveParking/{registerNo}")
    public Single<TicketSo> leaveParking(@PathVariable("registerNo") String registerNo){
        return ticketService.leaveParking(registerNo);
    }
}
