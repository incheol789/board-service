package org.boardservice.service;

import lombok.RequiredArgsConstructor;
import org.boardservice.domain.type.SearchType;
import org.boardservice.dto.ArticleDto;
import org.boardservice.dto.ArticleUpdateDto;
import org.boardservice.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

	private final ArticleRepository articleRepository;

	@Transactional(readOnly = true)
	public Page<ArticleDto> searchArticles(SearchType title, String search_keyword) {
		return Page.empty();
	}

	@Transactional(readOnly = true)
	public ArticleDto searchArticles(long articleId) {
		return null;
	}

	public void saveArticle(ArticleDto Dto) {
	}

	public void updateArticle(long articleId, ArticleUpdateDto dto) {
	}

	public void deleteArticle(long articleId) {

	}
}
