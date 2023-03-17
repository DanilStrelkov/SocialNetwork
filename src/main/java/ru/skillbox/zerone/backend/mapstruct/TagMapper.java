package ru.skillbox.zerone.backend.mapstruct;

import org.mapstruct.Mapper;
import ru.skillbox.zerone.backend.model.dto.request.TagDTO;
import ru.skillbox.zerone.backend.model.entity.Tag;


@Mapper
public interface TagMapper {
  Tag tagDTOToTag (TagDTO tagDTO);
  TagDTO tagToTagDTO (Tag tag);


}