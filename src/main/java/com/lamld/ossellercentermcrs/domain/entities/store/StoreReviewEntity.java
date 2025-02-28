package com.lamld.ossellercentermcrs.domain.entities.store;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import vn.mos.core.base.BaseEntity;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "store_review")
public class StoreReviewEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "store_branch_id", nullable = false)
  private Long storeBranchId;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = "rating", nullable = false)
  private Integer rating;

  @Column(name = "comment", length = 2000)
  private String comment;

  @Column(name = "files_upload_url")
  @JdbcTypeCode(SqlTypes.JSON)
  private List<String> filesUploadUrl;
}