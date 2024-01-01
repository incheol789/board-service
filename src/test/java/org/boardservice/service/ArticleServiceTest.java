package org.boardservice.service;

import org.boardservice.domain.Article;
import org.boardservice.domain.type.SearchType;
import org.boardservice.dto.ArticleDto;
import org.boardservice.dto.ArticleUpdateDto;
import org.boardservice.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

	@InjectMocks private ArticleService articleService;
	@Mock private ArticleRepository articleRepository;

	@DisplayName("게시글을 검색하면 게시글 리스트를 반환한다.")
	@Test
	public void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList() throws Exception {
	    //given

	    //when
		Page<ArticleDto> articles = articleService.searchArticles(SearchType.TITLE, "search Keyword"); // 제목, 본문, ID, 닉네임, 해시태그

	    //then
		assertThat(articles).isNotNull();

	}

	@DisplayName("게시글을 조회하면 게시글을 반환한다.")
	@Test
	public void givenArticleId_whenSearchingArticle_thenReturnsArticle() throws Exception {
		//given

		//when
		ArticleDto articles = articleService.searchArticles(1L); // 제목, 본문, ID, 닉네임, 해시태그

		//then
		assertThat(articles).isNotNull();
	}

	@DisplayName("게시글 정보를 입력하면, 게시글을 생성한다.")
	@Test
	public void givenArticleInfo_whenSavingArticle_thenSavesArticle() throws Exception {
	    //given
		given(articleRepository.save(any(Article.class))).willReturn(null);

	    //when
		articleService.saveArticle(ArticleDto.of(LocalDateTime.now(), "Jung", "title", "content", "#java"));

	    //then
		then(articleRepository).should().save(any(Article.class));

	}

	@DisplayName("게시글 ID와 수정 정보를 입력하면, 게시글을 수정한다.")
	@Test
	public void givenArticleIdAndModifiedInfo_whenUpdatingArticle_thenUpdatesArticle() throws Exception {
		//given
		given(articleRepository.save(any(Article.class))).willReturn(null);

		//when
		articleService.updateArticle(1L, ArticleUpdateDto.of("title", "content", "#java"));

		//then
		then(articleRepository).should().save(any(Article.class));

	}

	@DisplayName("게시글 ID를 입력하면, 게시글을 삭제한다.")
	@Test
	public void givenArticleId_whenDeletingArticle_thenDeletesArticle() throws Exception {
		//given
		willDoNothing().given(articleRepository).delete(any(Article.class));

		//when
		articleService.deleteArticle(1L);

		//then
		then(articleRepository).should().delete(any(Article.class));

	}

}