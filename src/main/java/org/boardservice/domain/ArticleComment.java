package org.boardservice.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@ToString
@Getter
@Table(indexes = {
		@Index(columnList = "content"),
		@Index(columnList = "createdAt"),
		@Index(columnList = "createdBy")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ArticleComment {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Article article; // 게시글 (ID)

	@Setter @Column(nullable = false, length = 500)
	private String content; // 본문

	@CreatedDate
	private LocalDateTime createdAt; // 생성일시
	@CreatedBy
	private String createdBy; // 생성자
	@LastModifiedDate
	private LocalDateTime modifiedAt; // 수정일시
	@LastModifiedBy
	private String modifiedBy; // 수정자

	private ArticleComment(Article article, String content) {
		this.article = article;
		this.content = content;
	}

	public static ArticleComment of(Article article, String content) {
		return new ArticleComment(article, content);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ArticleComment that)) return false;
		return id != null && id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
