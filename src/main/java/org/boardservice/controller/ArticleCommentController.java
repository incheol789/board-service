package org.boardservice.controller;

import lombok.RequiredArgsConstructor;
import org.boardservice.dto.UserAccountDto;
import org.boardservice.dto.request.ArticleCommentRequest;
import org.boardservice.service.ArticleCommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class ArticleCommentController {

	private final ArticleCommentService articleCommentService;

	@PostMapping("/new")
	public String postNewArticleComment(ArticleCommentRequest articleCommentRequest) {
		// TODO: 인증 정보 넣어줘야 한다.
		articleCommentService.saveArticleComment(articleCommentRequest.toDto(UserAccountDto.of(
				"jung", "pw", "jung@gmail.com", null, null
		)));

		return "redirect:/articles/" + articleCommentRequest.articleId();
	}

	@PostMapping("/{commentId}/delete")
	public String deleteArticleComment(@PathVariable Long commentId, Long articleId) {
		articleCommentService.deleteArticleComment(co);

		return "redirect:/articles/" + articleId;
	}
}
