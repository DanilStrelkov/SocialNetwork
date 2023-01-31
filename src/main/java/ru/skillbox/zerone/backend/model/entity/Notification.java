package ru.skillbox.zerone.backend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.skillbox.zerone.backend.model.enumerated.NotificationType;
import ru.skillbox.zerone.backend.model.enumerated.ReadStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification",
    indexes = {
        @Index(name = "notification_person_id_idx", columnList = "person_id")
    }
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Builder.Default
  @Enumerated(EnumType.STRING)
  @Column(name = "type_id", columnDefinition = "notification_type default 'POST'")
  private NotificationType typeId = NotificationType.POST;

  @NotNull
  @Builder.Default
  @Column(name = "sent_time", columnDefinition = "timestamp without time zone")
  private LocalDateTime sentTime = LocalDateTime.now();

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "person_id", referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "notification_person_fk")
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User person;

  @NotNull
  @Column(name = "entity_id")
  private Long entityId;

  @Builder.Default
  @Enumerated(EnumType.STRING)
  @Column(name = "status", columnDefinition = "read_status default 'SENT'")
  private ReadStatus status = ReadStatus.SENT;
}
