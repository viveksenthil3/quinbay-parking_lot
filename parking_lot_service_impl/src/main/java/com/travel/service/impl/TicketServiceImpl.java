package com.travel.service.impl;

import com.travel.entity.Car;
import com.travel.entity.Parking;
import com.travel.entity.Ticket;
import com.travel.repository.ParkingRepository;
import com.travel.repository.TicketRepository;
import com.travel.service.ParkingService;
import com.travel.service.TicketService;
import com.travel.so.CarSo;
import com.travel.so.ParkingSo;
import com.travel.so.TicketSo;
import io.reactivex.Single;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
//import rx.Single;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ParkingService parkingService;

    @Value("${parking.id}")
    private String PARKING_ID;


    @Override
    public Single<TicketSo> parkCar(CarSo carSo) {

        return Single.<TicketSo>create(subscriber -> {
            //convert CarSo to Car entity
            Car newCar = new Car();
            BeanUtils.copyProperties(carSo, newCar);

            //check if the car is already parked
            Ticket previousTicket = ticketRepository.findByRegisterNo(newCar.getRegisterNo());
            if(previousTicket!=null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

            //get the parking details
            ParkingSo parking = parkingService.findById(PARKING_ID);

            //find the first parking slot that is not occupied
            int parkingSize = parking.getIsOccupied().length;
            int slot=0;
            for(; slot<parkingSize; slot++)
                if(!parking.getIsOccupied()[slot])
                    break;

            //check if the parking is full
            if(slot>=parkingSize){
//                subscriber.onSuccess(null);
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//                return;
            }

            //crate a new ticket with the slot number that was found in the above step
            Ticket ticket = new Ticket();
            ticket.setCar(newCar);
            ticket.setOccupied(true);
            ticket.setParkingId(parking.getId());
            ticket.setParkingName(parking.getParkingName());
            ticket.setSlot(slot);
            System.out.println("ticket created");
            ticket = ticketRepository.save(ticket);


            //save the updated parking
            parking.getIsOccupied()[slot]=true;
            parkingService.save(parking);


            //convert the ticket entity to ticketSo
            TicketSo ticketSo = ticketToTicketSo(ticket);

            subscriber.onSuccess(ticketSo);
        });


//        return null;
    }

    @Override
    public Single<List<TicketSo>> getByColor(String color) {
        return Single.<List<TicketSo>>create(subscriber -> {
            List<Ticket> tickets = ticketRepository.findByColor(color);

            List<TicketSo> ticketSos = new ArrayList<>();
            for(Ticket ticket: tickets)
                ticketSos.add(ticketToTicketSo(ticket));

            subscriber.onSuccess(ticketSos);
        });
    }

    @Override
    public Single<TicketSo> getByRegisterNo(String registerNo) {
        return Single.<TicketSo>create(subscriber -> {
           Ticket ticket = ticketRepository.findByRegisterNo(registerNo);

//           List<TicketSo> ticketSos = new ArrayList<>();
//           for(Ticket ticket: tickets)
//               ticketSos.add(ticketToTicketSo(ticket));
            if(ticket==null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

            TicketSo ticketSo = ticketToTicketSo(ticket);
           subscriber.onSuccess(ticketSo);
        });
    }

    @Override
    public Single<TicketSo> leaveParking(String registerNo) {
        return Single.<TicketSo>create(subscribe -> {
            Ticket ticket = ticketRepository.findByRegisterNo(registerNo);

            if(ticket==null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

            ParkingSo parking = parkingService.findById(ticket.getParkingId());
            parking.getIsOccupied()[ticket.getSlot()]=false;
            parkingService.save(parking);

            ticketRepository.deleteById(ticket.getId());

            TicketSo deletedTicket = ticketToTicketSo(ticket);
            subscribe.onSuccess(deletedTicket);
        });
    }

    public TicketSo ticketToTicketSo(Ticket ticket){
        TicketSo ticketSo = new TicketSo();
        BeanUtils.copyProperties(ticket, ticketSo);

        CarSo savedCar = new CarSo();
        BeanUtils.copyProperties(ticket.getCar(), savedCar);
        ticketSo.setCar(savedCar);

        return ticketSo;
    }
}
