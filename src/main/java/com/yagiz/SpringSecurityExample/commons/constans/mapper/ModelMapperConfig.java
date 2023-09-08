package com.yagiz.SpringSecurityExample.commons.constans.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yagiz.SpringSecurityExample.commons.constans.mapper.mapperAbstracts.ModelMapperService;
import com.yagiz.SpringSecurityExample.commons.constans.mapper.mapperManager.ModelMapperManager;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    @Bean
    public ModelMapperService getModelMapperService(ModelMapper mapper){
        return new ModelMapperManager(mapper);
    }
}
