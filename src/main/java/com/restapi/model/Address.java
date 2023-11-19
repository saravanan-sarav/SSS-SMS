package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length =10)
    private String doorNum;

    @Column(nullable = false, length =30)
    private String street;


    private String addrLine;


    @Column(nullable = false, length =100)
    private String city;

    @Column(nullable = false, length =100)
    private String state;

    @Column(nullable = false, length =100)
    private String pincode;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created_at;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Parent parent;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Teacher teacher;

}
