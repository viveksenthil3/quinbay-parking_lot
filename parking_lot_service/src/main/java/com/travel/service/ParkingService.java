package com.travel.service;

import com.travel.entity.Parking;
import com.travel.so.ParkingSo;

public interface ParkingService {
    public ParkingSo findById(String parkingId);
    public ParkingSo save(ParkingSo parking);
}
