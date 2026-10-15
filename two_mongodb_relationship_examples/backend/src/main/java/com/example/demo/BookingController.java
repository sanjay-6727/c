package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:5173")
public class BookingController {
    @Autowired private BookingRepository repo;
    @Autowired private RoomRepository roomRepo;

    @GetMapping
    public List<Booking> all() {
        return repo.findAll();
    }

    @GetMapping("/room/{roomId}")
    public List<Booking> byRoom(@PathVariable String roomId) {
        return repo.findByRoomId(roomId);
    }

    @PostMapping
    public Booking add(@RequestBody Booking booking) {
        if (!roomRepo.existsById(booking.getRoomId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found");
        }
        if (booking.getCheckIn() == null || booking.getCheckOut() == null
                || !booking.getCheckOut().isAfter(booking.getCheckIn())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Check-out must be after check-in");
        }
        if (booking.getStatus() == null || booking.getStatus().isBlank()) {
            booking.setStatus("CONFIRMED");
        }
        return repo.save(booking);
    }

    @PutMapping("/{id}")
    public Booking update(@PathVariable String id, @RequestBody Booking booking) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found");
        }
        if (!roomRepo.existsById(booking.getRoomId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found");
        }
        if (booking.getCheckIn() == null || booking.getCheckOut() == null
                || !booking.getCheckOut().isAfter(booking.getCheckIn())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Check-out must be after check-in");
        }
        booking.setId(id);
        return repo.save(booking);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repo.deleteById(id);
    }
}
