package com.booking.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

    @NoArgsConstructor
    @Data
    @Entity
    @Table(name = "passengers")
    public class Passenger {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "booking_id", nullable = false)
        private Booking booking;

        @Column(nullable = false)
        private String firstName;

        @Column(nullable = false)
        private String lastName;

        @Column(nullable = false)
        private Integer age;

        @Column(nullable = false)
       // @Enumerated(EnumType.STRING)
        private String gender;

        @Column(nullable = false)
        private Integer seatNumber;

        @Column(nullable = false)
        @CreationTimestamp
        private LocalDateTime createdAt;

        @Column(nullable = false)
        @UpdateTimestamp
        private LocalDateTime updatedAt;

        // constructors, getters, and setters
    }