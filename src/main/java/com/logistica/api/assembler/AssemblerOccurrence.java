package com.logistica.api.assembler;

import com.logistica.api.model.OccurrenceModel;
import com.logistica.domain.model.Occurrence;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class AssemblerOccurrence {

    private ModelMapper modelMapper;

    public OccurrenceModel toModel(Occurrence occurrence) {
        return modelMapper.map(occurrence, OccurrenceModel.class);
    }

    public List<OccurrenceModel> tocollectionModel(List<Occurrence> occurrences) {
        return occurrences.stream().map(this::toModel).collect(Collectors.toList());
    }
}