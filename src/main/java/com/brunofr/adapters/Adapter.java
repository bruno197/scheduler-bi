package com.brunofr.adapters;

import java.util.List;

public interface Adapter <T, K> {
    public T toEntity(K dto);
    public List<T> toEntityList(List<K> dtoList);
}
