package com.travel.so;

import lombok.Data;

@Data
public class TicketSo {
    private String id;
    private CarSo car;
    private int slot;
    private String parkingName;
    private boolean occupied;
}
