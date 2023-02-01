package ru.skillbox.zerone.backend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.zerone.backend.model.enumerated.FriendshipCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "friendship_status")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipStatus {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotNull
  @Column(name = "time", columnDefinition = "timestamp without time zone")
  private LocalDateTime time;

  @NotNull
  @Column(name = "name")
  private String name;

  @NotNull
  @Column(name = "code", columnDefinition = "friendship_code")
  @Enumerated(EnumType.STRING)
  private FriendshipCode code;

  @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
  private List<Friendship> friendships = new ArrayList<>();
}