package org.boardservice.domain;

import javax.persistence.*;
import lombok.*;

import java.util.Objects;

@ToString(callSuper = true)
@Getter
@Table(indexes = {
		@Index(columnList = "content"),
		@Index(columnList = "createdAt"),
		@Index(columnList = "createdBy")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ArticleComment extends AuditingFields {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Article article; // 게시글 (ID)

	@Setter @ManyToOne(optional = false)
	@JoinColumn(name = "userId")
	private UserAccount userAccount; // 유저 정보 (ID)

	@Setter @Column(nullable = false, length = 500)
	private String content; // 본문


	private ArticleComment(Article article, UserAccount userAccount, String content) {
		this.article = article;
		this.userAccount = userAccount;
		this.content = content;
	}

	public static ArticleComment of(Article article, UserAccount userAccount, String content) {
		return new ArticleComment(article, userAccount, content);
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
