package co.develhope.customqueries2.repositories;

import co.develhope.customqueries2.entities.Flight;
import co.develhope.customqueries2.entities.FlightStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    Page<Flight> findAllByStatus(FlightStatusEnum status, Pageable pageable);

    @Query(value = "FROM flight WHERE status IN (:status1, :status2)")
    List<Flight> getCustomFlightsByTwoStatus(@Param("status1") FlightStatusEnum flightStatusEnum1, @Param("status2") FlightStatusEnum flightStatusEnum2);

}