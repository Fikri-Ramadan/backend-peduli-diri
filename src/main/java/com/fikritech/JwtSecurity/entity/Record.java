package com.fikritech.JwtSecurity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "records")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Record {

    @Id
    @GeneratedValue
    private Long id;
    private String createdBy;
    private LocalDate date;
    private String time;
    private String location;
    private int temperature;

}
