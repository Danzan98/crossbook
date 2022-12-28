package ru.example.mapper;

import org.springframework.stereotype.Component;
import ru.example.dto.ReaderDto;
import ru.example.mapper.mapper.AbstractBidirectionalMapper;
import ru.example.model.Reader;

@Component
public class ReaderMapper extends AbstractBidirectionalMapper<ReaderDto, Reader> {
    @Override
    public ReaderDto toSource(Reader target) {
        return ReaderDto.builder()
                .id(target.getId())
                .name(target.getName())
                .birthday(target.getBirthday())
                .bookAmount(target.getBookAmount())
                .build();
    }

    @Override
    public Reader toTarget(ReaderDto source) {
        Reader reader = new Reader();
        return toTarget(source, reader);
    }

    public Reader toTarget(ReaderDto source, Reader reader) {
        reader.setId(source.getId());
        reader.setName(source.getName());
        reader.setBirthday(source.getBirthday());
        reader.setBookAmount(source.getBookAmount());
        return reader;
    }
}
