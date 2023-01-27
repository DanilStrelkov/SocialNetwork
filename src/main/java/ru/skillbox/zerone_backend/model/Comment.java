package ru.skillbox.zerone_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comment")
@Data
@Builder
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name =  "id")
  private long id;

  @NotNull
  @Column(name = "time", columnDefinition = "timestamp without time zone")
  private LocalDateTime time;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false, referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "comment_post_fk")
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Post post;

  @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
  private List<BlockHistory> blockHistories = new ArrayList<>();

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private List<Comment> parents = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id", referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "comment_parent_comment_fk")
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Comment parent;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id", nullable = false, referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "comment_author_fk")
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User author;

  @NotNull
  @Column(name = "comment_test", columnDefinition = "text")
  private String commentText;

  @NotNull
  @Column(name = "is_blocked")
  private boolean isBlocked;

  @NotNull
  @Column(name = "is_deleted")
  private boolean isDeleted;
}
