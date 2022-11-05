package com.logistica.api.controller;

import com.logistica.api.assembler.AssemblerOccurrence;
import com.logistica.api.model.OccurrenceModel;
import com.logistica.api.model.input.OccurrenceInput;
import com.logistica.domain.model.Delivery;
import com.logistica.domain.model.Occurrence;
import com.logistica.domain.services.FindDeliveryService;
import com.logistica.domain.services.OccurrenceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/delivery/{deliveryId}/occurrence")
public class OccurrenceController {

    private FindDeliveryService findDeliveryService;
    private OccurrenceService occurrenceService;
    private AssemblerOccurrence assemblerOccurrence;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OccurrenceModel registration(@PathVariable Long deliveryId,@Valid @RequestBody OccurrenceInput occurrenceInput) {
        Occurrence occurrence = occurrenceService.registrationOccurrence(deliveryId, occurrenceInput.getDescription());
        return assemblerOccurrence.toModel(occurrence);
    }

    @GetMapping
    public List<OccurrenceModel> occurrenceModelList(@PathVariable Long deliveryId) {
        Delivery delivery = findDeliveryService.findDelivery(deliveryId);

        return assemblerOccurrence.tocollectionModel(delivery.getOccurrenceList());
    }
}