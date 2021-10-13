package com.travel.mongoChangelogs;

//import com.github.mongobee.changeset.ChangeLog;
//import com.github.mongobee.changeset.ChangeSet;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.travel.entity.Parking;
import com.travel.repository.ParkingRepository;
import com.travel.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@ChangeLog
public class ParkingCollectionChangeLog {
    @Value("${parking.id}")
    private String PARKING_ID_dummy;

    @Value("${parking.name}")
    private String PARKING_NAME_dummy;

    @Value("${parking.size}")
    private Integer PARKING_SIZE_dummy;

    private static String PARKING_ID;
    private static String PARKING_NAME;
    private static Integer PARKING_SIZE;

    @PostConstruct
    public void init(){
        ParkingCollectionChangeLog.PARKING_ID = PARKING_ID_dummy;
        ParkingCollectionChangeLog.PARKING_NAME = PARKING_NAME_dummy;
        ParkingCollectionChangeLog.PARKING_SIZE = PARKING_SIZE_dummy;
    }

    @ChangeSet(order = "001", id="setUpSlotCollection", author = "vivek", runAlways = true)
    public void initSlots(ParkingRepository parkingRepository){
//        System.out.println(ParkingCollectionChangeLog.pi);
        Parking parking = new Parking(PARKING_ID, PARKING_NAME, new boolean[PARKING_SIZE]);

        parkingRepository.save(parking);
//        System.out.println("hi vivek");
    }

}
