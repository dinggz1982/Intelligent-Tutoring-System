package edu.gzhu.its.experiment.web;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.gzhu.its.base.model.PageData;
import edu.gzhu.its.experiment.entity.Word;
import edu.gzhu.its.experiment.service.IWordService;

@Controller
public class TagViewController {
	
	@Resource
	private IWordService wordService;
	
	@GetMapping("/tagView")
	public String tagView(Model model) throws SQLException{
		
		List<Word> words = this.wordService.findAll();
		String wordString = "['学习',467],['学习动机',426],['学生',246],['知觉',122],['动机',122],['坚持',110]";
		model.addAttribute("wordString", wordString);
		//["Layout",846]
		for (Iterator iterator = words.iterator(); iterator.hasNext();) {
			Word word = (Word) iterator.next();
			//wordString
		}
		
		
		return "tagview/index";
	}

	/**
	 * 设定标签
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@GetMapping("/tag-setting")
	public String tagSetting(Integer pageIndex, Integer pageSize,Model model) throws SQLException{
		pageIndex = pageIndex == null ? 1 : pageIndex < 1 ? 1 : pageIndex;
		pageSize = 10;
		PageData<Word> pageData = this.wordService.getPageData(pageIndex, pageSize, "");
		model.addAttribute("dataList", pageData.getPageData());
		model.addAttribute("total", pageData.getTotalCount());
		model.addAttribute("pages", pageData.getTotalPage());
		model.addAttribute("pagesize", pageData.getPageSize());
		model.addAttribute("pageIndex", pageIndex);
		return "/tagview/wordList";
	}
	
	@GetMapping("/tag/add")
	public String tagAdd(){
		
		return "/tagview/add";
	}
}
