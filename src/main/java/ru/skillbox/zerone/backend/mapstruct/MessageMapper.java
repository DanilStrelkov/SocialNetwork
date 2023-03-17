package ru.skillbox.zerone.backend.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skillbox.zerone.backend.model.dto.request.MessageRequestDTO;
import ru.skillbox.zerone.backend.model.dto.response.MessageDataDTO;
import ru.skillbox.zerone.backend.model.entity.Dialog;
import ru.skillbox.zerone.backend.model.entity.Message;

import java.util.List;

@Mapper
public interface MessageMapper {
  @Mapping(target = "dialogId", source = "message.dialog.id")
  @Mapping(target = "time", source = "message.sentTime")
  @Mapping(target = "sendByMe", expression = "java(ru.skillbox.zerone.backend.util.CurrentUserUtils.getCurrentUser().equals(message.getAuthor()))")
  @Mapping(target = "authorId", source = "message.author.id")
  MessageDataDTO messageToMessageDataDTO (Message message);

  List<MessageDataDTO> messagesListToMessageDataDTOs (List<Message> messages);

  @SuppressWarnings("UnmappedTargetProperties")
  @Mapping(target = "author", expression = "java(ru.skillbox.zerone.backend.util.CurrentUserUtils.getCurrentUser())")
  @Mapping(target = "dialog", source = "dialog")
  @Mapping(target = "id", expression = "java(null)")
  Message messageRequestDTOToMessage(MessageRequestDTO messageRequestDTO, Dialog dialog);
}
