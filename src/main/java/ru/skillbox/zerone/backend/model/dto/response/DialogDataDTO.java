package ru.skillbox.zerone.backend.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DialogDataDTO {
  @JsonProperty("unread_count")
  private int unreadCount;
  @JsonProperty("last_message")
  private MessageDataDTO lastMessage;
  private long id;
  @JsonProperty("recipient_id")
  private UserDTO companion;
}