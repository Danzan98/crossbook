package ru.example.mapper.mapper;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractUnidirectionalMapper<S, T> implements UnidirectionalMapper<S, T> {

    @Override
    public List<T> toTarget(List<S> source) {
        if (source == null) {
            return null;
        }
        return source.stream()
                .map(this::toTarget)
                .collect(Collectors.toList());
    }
}
