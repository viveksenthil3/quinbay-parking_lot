package com.travel.service;

import com.travel.entity.Car;
import com.travel.entity.Ticket;
import com.travel.so.CarSo;
import com.travel.so.TicketSo;
import io.reactivex.Single;
import org.springframework.stereotype.Service;
//import rx.Single;

import java.util.List;

@Service
public interface TicketService {
    public Single<TicketSo> parkCar(CarSo carSo);
    public Single<List<TicketSo>> getByColor(String color);
    public Single<TicketSo> getByRegisterNo(String registerNo);
    public Single<TicketSo> leaveParking(String registerNo);
}
