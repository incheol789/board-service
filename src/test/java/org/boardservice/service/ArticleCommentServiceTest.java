package org.boardservice.service;

import org.boardservice.domain.Article;
import org.boardservice.domain.ArticleComment;
import org.boardservice.dto.ArticleCommentDto;
import org.boardservice.repository.ArticleCommentRepository;
import org.boardservice.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 게시글 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

	@InjectMocks private ArticleCommentService articleCommentService;

	@Mock ArticleCommentRepository articleCommentRepository;
	@Mock ArticleRepository articleRepository;

	@DisplayName("게시글 ID로 조회하면, 해당하는 댓글 리스트를 반환한다.")
	@Test
	public void givenArticleId_whenSearchingArticleComments_thenReturnsArticleComments() {
	    //given
		Long articleId = 1L;

		BDDMockito.given(articleRepository.findById(articleId)).willReturn(Optional.of(
				Article.of("title", "content", "#java")));

	    //when
		List<ArticleCommentDto> articleComments = articleCommentService.searchArticleComments(1L);

	    //then
		assertThat(articleComments).isNotNull();
		then(articleRepository).should().findById(articleId);

	}

	@DisplayName("댓글 정보를 입력하면, 해당하는 댓글을 저장한다.")
	@Test
	public void givenArticleCommentInfo_whenSavingArticleComment_thenSavesArticleComment() {
		//given
		given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);

		//when
		articleCommentService.saveArticleComment(ArticleCommentDto.of(LocalDateTime.now(), "Jung", LocalDateTime.now(), "Jung", "comment"));

		//then
		then(articleCommentRepository).should().save(any(ArticleComment.class));
	}


}