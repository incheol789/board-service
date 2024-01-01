package org.boardservice.service;

import lombok.RequiredArgsConstructor;
import org.boardservice.domain.type.SearchType;
import org.boardservice.dto.ArticleDto;
import org.boardservice.dto.ArticleWithCommentsDto;
import org.boardservice.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

	private final ArticleRepository articleRepository;

	@Transactional(readOnly = true)
	public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
		return Page.empty();
	}

	@Transactional(readOnly = true)
	public ArticleWithCommentsDto getArticle(Long articleId) {
		return null;
	}

	public void saveArticle(ArticleDto Dto) {
	}

	public void updateArticle(ArticleDto dto) {
	}

	public void deleteArticle(long articleId) {

	}
}
