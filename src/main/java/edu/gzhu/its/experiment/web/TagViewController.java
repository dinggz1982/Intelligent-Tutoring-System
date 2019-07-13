package edu.gzhu.its.experiment.web;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import edu.gzhu.its.base.model.PageData;
import edu.gzhu.its.base.util.TagUtils;
import edu.gzhu.its.experiment.entity.MyWord;
import edu.gzhu.its.experiment.entity.TagEditHistory;
import edu.gzhu.its.experiment.entity.Topic;
import edu.gzhu.its.experiment.entity.Word;
import edu.gzhu.its.experiment.model.WordModel;
import edu.gzhu.its.experiment.service.IMyWordService;
import edu.gzhu.its.experiment.service.ITagEditHistoryService;
import edu.gzhu.its.experiment.service.ITopicService;
import edu.gzhu.its.experiment.service.IWordService;
import edu.gzhu.its.system.entity.User;
import edu.gzhu.its.system.service.IUserService;

@Controller
public class TagViewController {

	@Autowired
	private HttpSession session;
	@Autowired
	private IWordService wordService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IMyWordService myWordService;

	@Autowired
	private ITopicService topicService;

	@Autowired
	private ITagEditHistoryService tagEditHistoryService;

	@GetMapping("/tagView")
	public String tagView(Model model) throws SQLException {

		List<Word> words = this.wordService.findAll();
		String wordString = "['学习',467,'#33ff00',10,20],['学习动机',426,'#33ff00',40,50],['学生',246,'#33ff00',50,70],['知觉',122,'#33ff00',200,100],['动机',122,'#33ff00',400,200],['坚持',110,'#33ff00',150,90]";
		model.addAttribute("wordString", wordString);
		// ["Layout",846]
		for (Iterator iterator = words.iterator(); iterator.hasNext();) {
			Word word = (Word) iterator.next();
			// wordString
		}

		return "tagview/index";
	}

	@GetMapping("/tagTest")
	public String tagTest(Model model) throws SQLException {
		List<Word> words = this.wordService.findAll();
		String wordString = "['学习',467,'#33ff00',10,20],['学习动机',426,'#33ff00',40,50],['学生',246,'#33ff00',50,70],['知觉',122,'#33ff00',200,100],['动机',122,'#33ff00',400,200],['坚持',110,'#33ff00',150,90]";
		model.addAttribute("wordString", wordString);
		// ["Layout",846]
		for (Iterator iterator = words.iterator(); iterator.hasNext();) {
			Word word = (Word) iterator.next();
			// wordString
		}
		return "tagview/tagTest";
	}

	/**
	 * 保存标签
	 * 
	 * @return
	 */
	@PostMapping("/saveMytag")
	@ResponseBody
	public Map<String, Object> saveMytag(String myWords, int topic_id) {
		// 获取当前用户
		User currentUser = (User) session.getAttribute("currentUser");
		Map<String, Object> map = new HashMap<>();
		JSONArray myJsonArray = JSONArray.parseArray(myWords);
		for (Iterator iterator = myJsonArray.iterator(); iterator.hasNext();) {
			JSONObject object = (JSONObject) iterator.next();
			int id = Integer.parseInt(object.get("id").toString());
			int positionX = Integer.parseInt(object.get("positionX").toString());
			int positionY = Integer.parseInt(object.get("positionY").toString());
			String color = object.get("color").toString();
			String size = object.get("size").toString();
			this.myWordService.executeSql("update myword set positionX=" + positionX + ",positionY=" + positionY
					+ ",color='" + color + "',size='" + size + "', createTime=now() where id=" + id);
		}
		map.put("status", "success");
		return map;
	}

	/**
	 * 设定标签
	 * 
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@GetMapping("/tag-setting")
	public String tagSetting(Integer pageIndex, Integer pageSize, Model model) throws SQLException {
		pageIndex = pageIndex == null ? 1 : pageIndex < 1 ? 1 : pageIndex;
		pageSize = 10;
		PageData<Topic> pageData = this.topicService.getPageData(pageIndex, pageSize, "");
		model.addAttribute("dataList", pageData.getPageData());
		model.addAttribute("total", pageData.getTotalCount());
		model.addAttribute("pages", pageData.getTotalPage());
		model.addAttribute("pagesize", pageData.getPageSize());
		model.addAttribute("pageIndex", pageIndex);
		return "/tagview/topList";
	}

	@GetMapping("/tag/topic/{id}")
	public String topicDetail(Integer pageIndex, Integer pageSize, Model model, @PathVariable Integer id)
			throws SQLException {
		pageIndex = pageIndex == null ? 1 : pageIndex < 1 ? 1 : pageIndex;
		pageSize = 10;
		PageData<Word> pageData = this.wordService.getPageData(pageIndex, pageSize, " and topic_id=" + id);
		model.addAttribute("dataList", pageData.getPageData());
		model.addAttribute("total", pageData.getTotalCount());
		model.addAttribute("pages", pageData.getTotalPage());
		model.addAttribute("pagesize", pageData.getPageSize());
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("topic_id", id);
		return "/tagview/wordList";
	}

	@GetMapping("/tag/editTag/{id}")
	public String editTag(@PathVariable int id, Model model) {
		User currentUser = (User) session.getAttribute("currentUser");
		List<MyWord> wordString = this.myWordService
				.find(" where user_id=" + currentUser.getId() + " and topic_id=" + id);
		if (wordString != null && wordString.size() > 0) {
			model.addAttribute("wordList", wordString);
			model.addAttribute("topic_id", id);
			model.addAttribute("isTaged", 1);
			return "/tagview/editTag";
		} else {
			List<Word> words = this.wordService.find(" where topic_id=" + id);
			model.addAttribute("words", words);
			model.addAttribute("topic_id", id);
			model.addAttribute("isTaged", 0);
			return "/tagview/editMyTagStep1";
		}

	}

	/**
	 * 选择相关词汇
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/tag/selectMyWord/{id}")
	public String editMyTag(@PathVariable int id, Model model) {
		User currentUser = (User) session.getAttribute("currentUser");
		List<Word> words = this.wordService.find(" where topic_id=" + id);
		List<MyWord> wordString = this.myWordService
				.find(" where user_id=" + currentUser.getId() + " and topic_id=" + id);
		model.addAttribute("words", words);
		model.addAttribute("topic_id", id);
		model.addAttribute("wordString", wordString);
		return "/tagview/editMyTagStep1";
	}

	/**
	 * 保存标签
	 * 
	 * @param id
	 * @param topic_id
	 * @param word
	 * @param model
	 * @return
	 */
	@PostMapping("/tag/saveMyWord")
	public String saveMyWord(int topic_id, String[] word, Model model) {
		User currentUser = (User) session.getAttribute("currentUser");
		int x = 20;
		int y = 20;
		this.myWordService
				.executeSql("delete from myword where topic_id=" + topic_id + " and user_id=" + currentUser.getId());
		List<MyWord> list = new ArrayList<MyWord>();
		for (int i = 0; i < word.length; i++) {
			if (word[i] != null && isNumber(word[i])) {
				int wordId = Integer.parseInt(word[i]);
				Word word2 = new Word();
				word2.setId(wordId);
				MyWord myWord = new MyWord();
				Topic topic = new Topic();
				topic.setId(topic_id);
				myWord.setTopic(topic);
				myWord.setUser(currentUser);
				myWord.setCreateTime(new Date());
				myWord.setPositionX(x + 10 * i);
				myWord.setPositionY(y + 10 * i);
				myWord.setColor("#000000");
				myWord.setSize("12");
				myWord.setWord(word2);
				list.add(myWord);
			}
		}
		this.myWordService.batchSave(list);
		return "redirect:/tag/editTag/" + topic_id;
	}

	public boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("^[0-9]*$");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/*
	 * 查看历史
	 * 
	 * @param pageIndex
	 * 
	 * @param pageSize
	 * 
	 * @param id
	 * 
	 * @param model
	 * 
	 * @return
	 */
	@GetMapping("/tag/history/{id}")
	public String history(Integer pageIndex, Integer pageSize, @PathVariable int id, Model model) {
		User currentUser = (User) session.getAttribute("currentUser");

		pageIndex = pageIndex == null ? 1 : pageIndex < 1 ? 1 : pageIndex;
		pageSize = 10;
		PageData<TagEditHistory> pageData = this.tagEditHistoryService.getPageData(pageIndex, pageSize,
				" and topic_id=" + id + " and user_id=" + currentUser.getId());
		model.addAttribute("dataList", pageData.getPageData());
		model.addAttribute("total", pageData.getTotalCount());
		model.addAttribute("pages", pageData.getTotalPage());
		model.addAttribute("pagesize", pageData.getPageSize());
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("topic_id", id);
		return "/tagview/history";
	}

	@GetMapping("/tag/add")
	public String tagAdd() {
		return "/tagview/add";
	}

	@PostMapping("/tag/saveWord")
	public String saveWord(Topic topic, String words) {
		this.topicService.save(topic);
		String[] oneWord = words.split("\n|\r\n|\r");
		for (int i = 0; i < oneWord.length; i++) {
			Word word = new Word();
			String[] newWord = oneWord[i].split(",");
			word.setWord(newWord[0]);
			word.setWeight(Float.parseFloat(newWord[1]));
			word.setFrequency(Integer.parseInt(newWord[2]));
			word.setTfidf(Float.parseFloat(newWord[3]));
			word.setTopic(topic);
			wordService.save(word);
		}
		return "redirect:/tag-setting";
	}

	/**
	 * 保存用户的操作记录
	 * 
	 * @param topic
	 * @param words
	 * @return
	 */
	@PostMapping("/saveTagEditHistory")
	@ResponseBody
	public String saveTagEditHistory(Integer id, Integer topic_id, String type, String word, Integer positionX,
			Integer positionY, String color, String size) {
		User currentUser = (User) session.getAttribute("currentUser");
		TagEditHistory history = this.tagEditHistoryService
				.getByHql("  topic_id=" + topic_id + " and user_id=" + currentUser.getId() + " order by id desc");

		TagEditHistory editHistory = new TagEditHistory();
		editHistory.setUser(currentUser);
		editHistory.setCreateTime(new Date());
		editHistory.setChangeType(type);
		Topic topic = new Topic();
		topic.setId(topic_id);
		editHistory.setTopic(topic);
		editHistory.setPositionX(positionX);
		editHistory.setPositionY(positionY);
		editHistory.setSize(size);
		editHistory.setColor(color);
		editHistory.setChangeType(type);
		editHistory.setWordString(word);
		MyWord myWord = this.myWordService.findById(id);
		editHistory.setWord(myWord.getWord());
		String operateInfo = "";
		if (history != null) {
			if (type.equals("move")) {
				operateInfo = word + ":移动标签,(" + history.getPositionX() + "," + history.getPositionY() + ")-->("
						+ positionX + "," + positionY + ")";
			}
			if (type.equals("color")) {
				operateInfo = word + ":修改颜色," + history.getColor() + ")-->" + color;
			}
			if (type.equals("size")) {
				operateInfo = word + ":修改大小," + history.getSize() + ")-->" + size;
			}
		}
		editHistory.setOperateInfo(operateInfo);
		this.tagEditHistoryService.save(editHistory);
		return "success";
	}

	// 上传文件会自动绑定到MultipartFile中
	@PostMapping("/tag/uploadTag")
	public String upload(HttpServletRequest request, Topic topic, @RequestParam("file") MultipartFile file)
			throws Exception {
		// 如果文件不为空，写入上传路径
		if (!file.isEmpty()) {
			// 上传文件路径
			String path = request.getServletContext().getRealPath("/WEB-INF/uploads/");
			// 上传文件名
			String filename = file.getOriginalFilename();
			File filepath = new File(path, filename);
			// 判断路径是否存在，如果不存在就创建一个
			if (!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();
			}
			// 将上传文件保存到一个目标文件当中
			file.transferTo(new File(path + File.separator + filename));
			this.topicService.save(topic);
			// 输出文件上传最终的路径 测试查看
			List<Word> words = TagUtils.getTags(path + File.separator + filename, topic);

			wordService.batchSave(words);

		}
		return "redirect:/tag/editTopic/" + topic.getId();
	}

	@GetMapping("/tag/editTopic/{id}")
	public String editTopic(@PathVariable Integer id, Model model) {
		Topic topic = this.topicService.findById(id);
		model.addAttribute("topic", topic);
		List<Word> words = this.wordService.find(" where topic_id=" + id);
		model.addAttribute("words", words);
		return "/tagview/editTopic";
	}

	/**
	 * 更新主题
	 * 
	 * @param request
	 * @param topic
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/tag/updateTopic")
	public String updateTopic(HttpServletRequest request, Topic topic, String[] words, String[] weigths,
			String[] frequencys, String[] tfidfs) throws Exception {

		this.topicService.update(topic);

		this.wordService.executeSql("delete from word where tpoic_id=" + topic.getId());

		return "redirect:/tag/editTopic/" + topic.getId();
	}

	/**
	 * topic List
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@GetMapping("/tag/topicList")
	public String topicList(Integer pageIndex, Integer pageSize, Model model) throws SQLException {
		pageIndex = pageIndex == null ? 1 : pageIndex < 1 ? 1 : pageIndex;
		pageSize = 10;
		PageData<Topic> pageData = this.topicService.getPageData(pageIndex, pageSize, "");
		model.addAttribute("dataList", pageData.getPageData());
		model.addAttribute("total", pageData.getTotalCount());
		model.addAttribute("pages", pageData.getTotalPage());
		model.addAttribute("pagesize", pageData.getPageSize());
		model.addAttribute("pageIndex", pageIndex);
		return "/tagview/topicList";
	}

	/**
	 * 显示每个主题的标签对应的用户
	 * 
	 * @param id
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@GetMapping("/userTag/{id}")
	public String userTag(@PathVariable Integer id, Integer pageIndex, Integer pageSize, Model model) {
		pageIndex = pageIndex == null ? 1 : pageIndex < 1 ? 1 : pageIndex;
		pageSize = 10;
		PageData<User> pageData = this.userService.getPageData(pageIndex, pageSize,
				" and id in (select DISTINCT user.id from edu.gzhu.its.experiment.entity.MyWord  where topic.id=" + id
						+ ")");
		Topic topic = this.topicService.findById(id);
		model.addAttribute("topic", topic);
		model.addAttribute("dataList", pageData.getPageData());
		model.addAttribute("total", pageData.getTotalCount());
		model.addAttribute("pages", pageData.getTotalPage());
		model.addAttribute("pagesize", pageData.getPageSize());
		model.addAttribute("pageIndex", pageIndex);
		return "/tagview/userTagList";
	}

	/**
	 * 根据用户和主题新显示对应的标签操作
	 * 
	 * @param topic_id
	 * @param user_id
	 * @param model
	 * @return
	 */
	@GetMapping("/showUserTag/{topic_id}/{user_id}")
	public String showUserTag(@PathVariable int topic_id, @PathVariable long user_id, Model model) {
		List<MyWord> wordString = this.myWordService.find(" where user_id=" + user_id + " and topic_id=" + topic_id);
		User user = this.userService.findById(user_id);
		model.addAttribute("user", user);
		Topic topic = this.topicService.findById(topic_id);
		model.addAttribute("topic", topic);
		model.addAttribute("wordList", wordString);
		model.addAttribute("topic_id", topic_id);
		model.addAttribute("user_id", user_id);
		model.addAttribute("isTaged", 1);
		return "/tagview/showUserTag";
	}

	/**
	 * 显示用户的操作记录
	 * @param pageIndex
	 * @param pageSize
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/tag/userhistory/{topic_id}/{user_id}")
	public String userhistory(Integer pageIndex, Integer pageSize, @PathVariable int topic_id, @PathVariable long user_id,Model model) {
		pageIndex = pageIndex == null ? 1 : pageIndex < 1 ? 1 : pageIndex;
		pageSize = 10;
		User user = this.userService.findById(user_id);
		model.addAttribute("user", user);
		Topic topic = this.topicService.findById(topic_id);
		model.addAttribute("topic", topic);
		PageData<TagEditHistory> pageData = this.tagEditHistoryService.getPageData(pageIndex, pageSize,
				" and topic_id=" + topic_id + " and user_id=" + user_id);
		model.addAttribute("dataList", pageData.getPageData());
		model.addAttribute("total", pageData.getTotalCount());
		model.addAttribute("pages", pageData.getTotalPage());
		model.addAttribute("pagesize", pageData.getPageSize());
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("topic_id", topic_id);
		model.addAttribute("user_id", user_id);
		return "/tagview/userhistory";
	}
	
}
