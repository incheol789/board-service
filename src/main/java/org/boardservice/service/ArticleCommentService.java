package org.boardservice.service;

import lombok.RequiredArgsConstructor;
import org.boardservice.dto.ArticleCommentDto;
import org.boardservice.repository.ArticleCommentRepository;
import org.boardservice.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService {

	private final ArticleRepository articleRepository;
	private final ArticleCommentRepository articleCommentRepository;

	@Transactional(readOnly = true)
	public List<ArticleCommentDto> searchArticleComment(Long articleId) {
		return List.of();
	}

	public void saveArticleComment(ArticleCommentDto dto) {
	}

}