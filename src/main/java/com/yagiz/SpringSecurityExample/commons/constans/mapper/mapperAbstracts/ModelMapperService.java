package com.yagiz.SpringSecurityExample.commons.constans.mapper.mapperAbstracts;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
    ModelMapper forRequest();
    ModelMapper forResponse();
}
