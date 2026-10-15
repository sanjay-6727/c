package com.example.demo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking, String> {
    List<Booking> findByRoomId(String roomId);
    List<Booking> findByStatus(String status);
}
