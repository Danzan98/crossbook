package ru.example.mapper.mapper;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractBidirectionalMapper<S, T> extends AbstractUnidirectionalMapper<S, T>
        implements BidirectionalMapper<S, T> {

    @Override
    public List<S> toSource(List<T> target) {
        if (target == null) {
            return null;
        }
        return target.stream()
                .map(this::toSource)
                .collect(Collectors.toList());
    }
}
