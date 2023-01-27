package ru.skillbox.zerone_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "friendship")
@Data
@Builder
public class Friendship {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "status_id", nullable = false,
      referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "friendship_friendship_status_fk")
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  private FriendshipStatus status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "src_person_id", nullable = false,
      referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "friendship_src_person_fk")
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User srcPerson;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dst_person_id", nullable = false,
      referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "friendship_dst_person_fk")
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User dstPerson;

  @NotNull
  @Column(name = "time", columnDefinition = "timestamp without time zone")
  private LocalDateTime time;
}
