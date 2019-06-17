package edu.gzhu.its.corpus;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.gzhu.its.corpus.entity.UserRemark;
import edu.gzhu.its.corpus.service.IUserCommentService;
import edu.gzhu.its.corpus.service.IUserRemarkService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateUserCommentTest {
	
	@Autowired
	private IUserCommentService userCommentService;
	@Autowired
	private IUserRemarkService userRemarkService;
	
	@Test
	public void update(){
		
		List<UserRemark> remarks = this.userRemarkService.find(" where userComment_id=1");
		
		if(remarks!=null){
			Map<Integer, UserRemark> map = new HashMap<Integer, UserRemark>();
			int i=0;
			for (Iterator iterator = remarks.iterator(); iterator.hasNext();) {
				UserRemark userRemark = (UserRemark) iterator.next();
				map.put(i, userRemark);
				i++;
			}
			
			
		}
		
		
	}

}
