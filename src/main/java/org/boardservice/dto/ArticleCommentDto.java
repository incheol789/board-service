package org.boardservice.dto;

import java.time.LocalDateTime;


public record ArticleCommentDto(
		Long id,
		Long articleId,
		UserAccountDto userAccountDto,
		String content,
		LocalDateTime createdAt,
		String createdBy,
		LocalDateTime modifiedAt,
	}

	public ArticleComment toEntity(Article entity) {
		return ArticleComment.of(
				entity,
				userAccountDto.toEntity(),
				content
		);
	}

}
