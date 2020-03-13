package com.potato112.springdemo.crud.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(
                name = "getAllRentalAgreements",
                query = "select ra from RentalAgreement ra order by ra.validFrom DESC"
        ),
        @NamedQuery(
                name = "deleteAllRentalAgreements",
                query = "delete from RentalAgreement ra"
        ),
        @NamedQuery(
                name = "deleteRentalAgreementById",
                query = "delete from RentalAgreement ra where ra.agreementId = :agreementId"
        )
})


@Entity
@Table(schema = "demo-db", name = "rental_agreement")
public class RentalAgreement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agreementId;
    private LocalDateTime validFrom;
    private String notes;

    @ManyToOne
    private RentalCar rentalCar;

    @ManyToOne
    private RentalClient rentalClient;


    public Long getAgreementId() {
        return agreementId;
    }

    public void setRentalAgreementId(Long rentalAgreementId) {
        this.agreementId = rentalAgreementId;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public RentalCar getRentalCar() {
        return rentalCar;
    }

    public void setRentalCar(RentalCar rentalCar) {
        this.rentalCar = rentalCar;
    }

    public RentalClient getRentalClient() {
        return rentalClient;
    }

    public void setRentalClient(RentalClient rentalClient) {
        this.rentalClient = rentalClient;
    }
}
