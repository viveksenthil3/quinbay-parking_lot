package com.travel.repository;

import com.travel.entity.Ticket;
//import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {
    @Query(value = "{ 'car.color' : ?0 }")
    public List<Ticket> findByColor(String color);

    @Query(value = "{ 'car.registerNo' : ?0 }")
    public Ticket findByRegisterNo(String registerNo);
}
