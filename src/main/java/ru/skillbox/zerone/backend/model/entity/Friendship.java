package ru.skillbox.zerone.backend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "friendship",
    indexes = {
        @Index(name = "friendship_status_id_idx", columnList = "status_id"),
        @Index(name = "friendship_src_person_id_idx", columnList = "src_person_id"),
        @Index(name = "friendship_dst_person_id_idx", columnList = "dst_person_id")
    }
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Friendship {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "status_id", referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "friendship_friendship_status_fk")
  )
  private FriendshipStatus status;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "src_person_id", referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "friendship_src_person_fk")
  )
  private User srcPerson;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "dst_person_id", referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "friendship_dst_person_fk")
  )
  private User dstPerson;

  @NotNull
  @Builder.Default
  @Column(name = "time", columnDefinition = "timestamp without time zone")
  private LocalDateTime time = LocalDateTime.now();
}
