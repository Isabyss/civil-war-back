package com.basementinteractive.civilwar.common.mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestToEntityMapper<T, U> {

  private final ModelMapper modelMapper;

  public U map(T from, U to) {
    modelMapper.map(from, to);
    return to;
  }

  public U map(T from, Class<U> destinationType) {
    return modelMapper.map(from, destinationType);
  }

}
