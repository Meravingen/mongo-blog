package by.project.my.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;

import by.project.my.model.Post;

@Repository
public class PostDao {

	@Autowired
	private MongoOperations mongoOperations;

	private static final Logger log = Logger.getLogger(PostDao.class);

	public Post findPostbyPermalink(String permalink) {
		return mongoOperations.findOne(
				Query.query(Criteria.where("permalink").is(permalink)),
				Post.class);
	}

	public List<Post> findByDateDescending(int limit) {
		Query query = new Query().with(sortByDateDesc()).limit(limit);
		return mongoOperations.find(query, Post.class);
	}

	public List<Post> findByTagDateDescending(final String tag) {
		return mongoOperations.find(Query.query(Criteria.where("tag").is(tag))
				.with(sortByDateDesc()).limit(10), Post.class);
	}

	private Sort sortByDateDesc() {
		return new Sort(Sort.Direction.DESC, "date");
	}

	public String addPost(String title, String body, List<String> tags,
			String username) {
		String permalink = title.replaceAll("\\s", "_"); // whitespace becomes _
		permalink = permalink.replaceAll("\\W", ""); // get rid of non
														// alphanumeric
		permalink = permalink.toLowerCase();
		Post post = new Post();
		post.setId((new BasicDBObject()).getString("_id"));
		post.setTitle(title);
		post.setAuthor(username);
		post.setBody(body);
		post.setPermalink(permalink);
		post.setTags(tags);
		post.setComments(new ArrayList<String>());
		post.setDate(new Date());
		try {
			mongoOperations.insert(post);
			log.info("Inserting blogpost with permalink " + permalink);
		} catch (Exception e) {
			log.error("Error inserting post");
		}
		return permalink;
	}

	// not yet implemented
	public void addPostComment(final String name, final String email,
			final String body, final String permalink) {
	}

}
