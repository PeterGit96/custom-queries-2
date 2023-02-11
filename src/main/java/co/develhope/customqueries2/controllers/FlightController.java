package co.develhope.customqueries2.controllers;

import co.develhope.customqueries2.entities.Flight;
import co.develhope.customqueries2.entities.FlightStatusEnum;
import co.develhope.customqueries2.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createFlights(@RequestParam(required = false) Integer n) {

        if(n == null) {
            n = 100;
        }

        List<Flight> flights = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            String description = generateRandomString(10, 50);
            String fromAirport = generateRandomString(8, 20);
            String toAirport = generateRandomString(8, 20);
            flights.add(new Flight(null, description, fromAirport, toAirport, this.getRandomStatus()));
        }

        flightRepository.saveAll(flights);
        return ResponseEntity.status(HttpStatus.CREATED).body("Flights created successfully!");
    }

    @GetMapping
    public Page<Flight> getFlights(@RequestParam int page, @RequestParam int size) {
        return flightRepository.findAll(PageRequest.of(page, size, Sort.by("fromAirport").ascending()));
    }

    @GetMapping("/status/{status}")
    public Page<Flight> getAllFlightsByStatus(@PathVariable FlightStatusEnum status, @RequestParam int page, @RequestParam int size) {
        return flightRepository.findAllByStatus(status, (PageRequest.of(page, size)));
    }

    @GetMapping("/custom")
    public List<Flight> getCustomFlightsByTwoStatus(@RequestParam FlightStatusEnum p1, @RequestParam FlightStatusEnum p2) {
        return flightRepository.getCustomFlightsByTwoStatus(p1, p2);
    }

    private String generateRandomString(int min, int max) {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        //int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(random.ints(min, (max + 1)).limit(1).findFirst().getAsInt())
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public FlightStatusEnum getRandomStatus() {
        return FlightStatusEnum.values()[new Random().nextInt(FlightStatusEnum.values().length)];
    }

}
