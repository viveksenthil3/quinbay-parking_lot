package com.travel.response;

import com.travel.vo.CarVo;
import lombok.Data;

@Data
public class ParkCarResponse {

    private CarVo car;
    private int slot;
    private String parkingName;
    private boolean occupied;
}
