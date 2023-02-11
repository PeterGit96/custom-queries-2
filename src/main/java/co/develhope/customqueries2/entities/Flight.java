package co.develhope.customqueries2.entities;

import javax.persistence.*;

@Entity(name = "flight")
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String fromAirport;
    private String toAirport;
    private FlightStatusEnum status;

    public Flight() { }

    public Flight(Long id, String description, String fromAirport, String toAirport, FlightStatusEnum status) {
        this.id = id;
        this.description = description;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public String getToAirport() {
        return toAirport;
    }

    public FlightStatusEnum getStatus() {
        return status;
    }

}
