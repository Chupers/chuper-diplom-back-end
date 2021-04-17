package com.chuper.diplom.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class AccommodationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accommodationInfoId;

    @OneToOne
    private Accommodation accommodation;

    @NotNull
    private String country;

    @NotNull
    private String city;

    @NotNull
    private String address;

    @ElementCollection
    private Set<String> photo;

    private String longitude;

    private String latitude;

    private Integer guestCount;

    private Integer roomCount;

    private Integer bedCount;

    private String description;

    private BigInteger pricePerDay;

    public AccommodationInfo() {
    }
}
