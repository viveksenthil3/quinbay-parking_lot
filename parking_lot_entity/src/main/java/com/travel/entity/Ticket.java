package com.travel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "ticket")
public class Ticket {
    @Id
    private String id;
    private Car car;
    private int slot;
    private String parkingId;
    private String parkingName;
    private boolean occupied;
}
