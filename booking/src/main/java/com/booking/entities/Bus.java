package com.booking.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@Data

@Entity
@Table(name = "buses")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "operator_id")
    private BusOperator busOperator;

    @Column(name = "bus_type")
    private String busType;

    @Column(name = "total_seats")
    private int totalSeats;

    @Column(name = "amenities")
    private String amenities;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<Schedule> schedules = new ArrayList<>();
}