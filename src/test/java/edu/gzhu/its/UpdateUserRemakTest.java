package edu.gzhu.its;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.gzhu.its.corpus.entity.UserComment;
import edu.gzhu.its.corpus.service.IUserCommentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateUserRemakTest {
	@Resource
	private IUserCommentService userCommentService;
	
	@Test
	@Transactional
	@Rollback(false)
	public void test() throws SQLException{
		List<UserComment> comments = this.userCommentService.findAll();
		Set<String> strings = new HashSet<String>();
		for (Iterator iterator = comments.iterator(); iterator.hasNext();) {
			UserComment userComment = (UserComment) iterator.next();
			if(strings.contains(userComment.getContent())){
				userComment.setDelFlag(true);
				this.userCommentService.update(userComment);
			}else{
				strings.add(userComment.getContent());
			}
			
		}
	}
}
