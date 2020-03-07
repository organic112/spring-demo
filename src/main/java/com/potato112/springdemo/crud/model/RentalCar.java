package com.potato112.springdemo.crud.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "getAllRentalCars",
                query = "select rce from RentalCar rce order by rce.brand DESC"
        ),
        @NamedQuery(
                name = "deleteAllRentalCars",
                query = "delete from RentalCar rce"
        ),
        @NamedQuery(
                name = "deleteRentalCarsById",
                query = "delete from RentalCar rce where rce.carId = :carId"
        )
})

@Entity
public class RentalCar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    private String brand;
    private int payloadKG;
    private String color;

    @Column(columnDefinition = "DECIMAL(19,6)") // accuracy of big decimal stored in db
    private BigDecimal pricePerHour;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "rentalCar", cascade = CascadeType.ALL)
    private List<RentalAgreement> rentalAgreements = new ArrayList<>();

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPayloadKG() {
        return payloadKG;
    }

    public void setPayloadKG(int payloadKG) {
        this.payloadKG = payloadKG;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public List<RentalAgreement> getRentalAgreements() {
        return rentalAgreements;
    }

    public void setRentalAgreements(List<RentalAgreement> rentalAgreements) {
        this.rentalAgreements = rentalAgreements;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
