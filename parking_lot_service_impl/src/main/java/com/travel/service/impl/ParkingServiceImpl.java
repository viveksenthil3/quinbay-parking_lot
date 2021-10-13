package com.travel.service.impl;

import com.travel.entity.Parking;
import com.travel.repository.ParkingRepository;
import com.travel.service.ParkingService;
import com.travel.so.ParkingSo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParkingServiceImpl implements ParkingService {
    @Autowired
    ParkingRepository parkingRepository;

    @Override
    public ParkingSo findById(String parkingId) {
        Optional<Parking> parking = parkingRepository.findById(parkingId);

        if(!parking.isPresent())
            return null;

        ParkingSo parkingSo = new ParkingSo();
        BeanUtils.copyProperties(parking.get(), parkingSo);
        return parkingSo;
    }

    @Override
    public ParkingSo save(ParkingSo parkingSo) {
        Parking parking = new Parking();
        BeanUtils.copyProperties(parkingSo, parking);

        Parking savedParking = parkingRepository.save(parking);
        ParkingSo savedParkingSo = new ParkingSo();
        BeanUtils.copyProperties(savedParking, savedParkingSo);

        return savedParkingSo;
    }
}
