package org.boardservice.repository;

import org.boardservice.config.JpaConfig;
import org.boardservice.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

	private final ArticleRepository articleRepository;
	private final ArticleCommentRepository articleCommentRepository;

	JpaRepositoryTest(@Autowired ArticleRepository articleRepository,
					  @Autowired ArticleCommentRepository articleCommentRepository) {
		this.articleRepository = articleRepository;
		this.articleCommentRepository = articleCommentRepository;
	}

	@Test
	@DisplayName("select 테스트")
	public void givenTestData_whenSelecting_thenWorksFine() throws Exception {
	    //given

	    //when
		List<Article> articles = articleRepository.findAll();

	    //then
		assertThat(articles).isNotNull().hasSize(123);

	}

	@Test
	@DisplayName("insert 테스트")
	public void givenTestData_whenInserting_thenWorksFine() throws Exception {
		//given
		long previousCount = articleRepository.count();

		//when
		Article savedArticle = articleRepository.save(Article.of("new article", "new content", "#spring"));

		//then
		assertThat(articleRepository.count()).isEqualTo(previousCount + 1);

	}

	@Test
	@DisplayName("update 테스트")
	public void givenTestData_whenUpdating_thenWorksFine() throws Exception {
		//given
		Article article = articleRepository.findById(1L).orElseThrow();
		String updatedHashtag = "#springboot";
		article.setHashtag(updatedHashtag);

		//when
		Article savedArticle = articleRepository.save(article);

		//then
		assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);

	}

	@Test
	@DisplayName("delete 테스트")
	public void givenTestData_whenDeleting_thenWorksFine() throws Exception {
		//given
		Article article = articleRepository.findById(1L).orElseThrow();
		long previousArticleCount = articleRepository.count();
		long previousArticleComment = articleCommentRepository.count();
		int deletedCommentSize = article.getArticleComments().size();

		//when
		articleRepository.delete(article);

		//then
		assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
		assertThat(articleCommentRepository.count()).isEqualTo(previousArticleComment - deletedCommentSize);
	}
}