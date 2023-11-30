package com.basementinteractive.civilwar.common.mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Component
public class EntityToResponseMapper<T, U> {

  private final ModelMapper modelMapper;

  public U map(T from, Class<U> destinationType) {
    return modelMapper.map(from, destinationType);
  }

  public List<U> mapToList(Collection<T> from, Class<U> destinationType) {
    return from.stream().map(element -> this.map(element, destinationType)).toList();
  }

  public Page<U> mapToPage(Page<T> from, Class<U> destinationType) {
    return from.map(element -> this.map(element, destinationType));
  }

}
