package com.yagiz.SpringSecurityExample.commons.constans.mapper.mapperManager;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import com.yagiz.SpringSecurityExample.commons.constans.mapper.mapperAbstracts.ModelMapperService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ModelMapperManager implements ModelMapperService {
    private final ModelMapper modelMapper;

    @Override
    public ModelMapper forRequest() {
        modelMapper.getConfiguration()
            .setAmbiguityIgnored(true)
            .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;
    }

    @Override
    public ModelMapper forResponse() {
        modelMapper.getConfiguration()
            .setAmbiguityIgnored(true)
            .setMatchingStrategy(MatchingStrategies.STANDARD);
        return modelMapper;
    }
}
