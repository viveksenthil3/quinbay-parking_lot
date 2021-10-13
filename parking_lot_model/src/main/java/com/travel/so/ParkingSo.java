package com.travel.so;

import lombok.Data;

@Data
public class ParkingSo {
    private String id;
    private String parkingName;
    private boolean[] isOccupied;
}
