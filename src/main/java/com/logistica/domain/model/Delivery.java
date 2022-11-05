package com.logistica.domain.model;

import com.logistica.domain.exception.DomainException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Delivery {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @Embedded
    private Addressee addressee;

    private BigDecimal tax;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Occurrence> occurrenceList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private OffsetDateTime dateOrder;

    private OffsetDateTime dateFinished;

    public Occurrence addOccurrence(String description) {
        Occurrence occurrence = new Occurrence();
        occurrence.setDescription(description);
        occurrence.setRegistrationDate(OffsetDateTime.now());
        occurrence.setDelivery(this);

        this.getOccurrenceList().add(occurrence);

        return occurrence;
    }

    public void finishDelivery() {
        if (!canBeFinished()) {
            throw new DomainException("Delivery cannot be completed.");
        }
        setStatus(DeliveryStatus.FINISHED);
        setDateFinished(OffsetDateTime.now());
    }

    public boolean canBeFinished() {
        return DeliveryStatus.PENDING.equals(getStatus());
    }
}