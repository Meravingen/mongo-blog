package by.project.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.project.my.dao.PostDao;
import by.project.my.model.Comment;
import by.project.my.model.Post;

@Service
public class PostService {

	@Autowired
	private PostDao postDao;

	public Post findPostByPermalink(String permalink) {
		return postDao.findPostbyPermalink(permalink);
	}

	public List<Post> findByDateDescending(int limit) {
		return postDao.findByDateDescending(limit);
	}

	public List<Post> findByTagDateDescending(final String tag) {
		return postDao.findByTagDateDescending(tag);
	}

	public String addPost(String title, String body, List<String> tags,
			String username) {
		return postDao.addPost(title, body, tags, username);
	}

	public void addPostComment(Post post, Comment comment) {
		postDao.addPostComment(post, comment);
	}

}
