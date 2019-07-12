package edu.gzhu.its.system.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.gzhu.its.base.model.PageData;
import edu.gzhu.its.base.model.TableSplitResult;
import edu.gzhu.its.base.util.TagUtils;
import edu.gzhu.its.base.util.UploadUserUtils;
import edu.gzhu.its.experiment.entity.Word;
import edu.gzhu.its.system.entity.Resource;
import edu.gzhu.its.system.entity.ResourceButton;
import edu.gzhu.its.system.entity.User;
import edu.gzhu.its.system.service.IResourceButtonService;
import edu.gzhu.its.system.service.IResourceService;
import edu.gzhu.its.system.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IResourceButtonService resourceButtonService;
	@Autowired
	private IResourceService resourceService;

	@Autowired
	private HttpSession session;

	@Autowired
	private HttpServletRequest request;

	/**
	 * 通过文件导入用户
	 * 
	 * @return
	 */
	@GetMapping("/addFromFile")
	public String addFromFile() {
		return "system/user/addFromFile";
	}

	/**
	 * 用户列表
	 * <p>
	 * 方法名:list
	 * </p>
	 * <p>
	 * Description :
	 * </p>
	 * <p>
	 * Company :
	 * </p>
	 * 
	 * @author 丁国柱
	 * @date 2018年1月30日 下午11:15:01
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@GetMapping("/list")
	public String list(Integer pageIndex, Integer pageSize, Model model) {
		pageIndex = pageIndex == null ? 1 : pageIndex < 1 ? 1 : pageIndex;
		pageSize = 10;
		PageData<User> pageData = this.userService.getPageData(pageIndex, pageSize, "");
		model.addAttribute("dataList", pageData.getPageData());
		model.addAttribute("total", pageData.getTotalCount());
		model.addAttribute("pages", pageData.getTotalPage());
		model.addAttribute("pagesize", pageData.getPageSize());
		model.addAttribute("pageIndex", pageIndex);

		// 取得用户的按钮权限
		User currentUser = (User) session.getAttribute("currentUser");

		System.out.println(request.getRequestURI());
		String url = request.getRequestURI();
		Resource resource = resourceService.getResourceByURL(url);
		Set<ResourceButton> buttons = this.resourceButtonService.getResourceButtonByUserId(resource, currentUser);
		model.addAttribute("buttons", buttons);

		return "system/user/list";
	}

	@GetMapping("/user/userList1")
	@ResponseBody
	public TableSplitResult<User> userList1(Integer pageIndex, Integer pageSize) {
		pageIndex = pageIndex == null ? 1 : pageIndex < 1 ? 1 : pageIndex;
		pageSize = 10;

		PageData<User> pageData = this.userService.getPageData(pageIndex, pageSize, "");
		TableSplitResult<User> pageJson = new TableSplitResult<User>();
		pageJson.setTotal(pageData.getTotalCount());
		pageJson.setRows(pageData.getPageData());
		pageJson.setPage(pageIndex);
		return pageJson;
	}

	/**
	 * 实现文件上传
	 * 
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@PostMapping("/saveUserFromFile")
	public String saveUserFromFile(@RequestParam("file") MultipartFile file, Model model)
			throws IllegalStateException, IOException {

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
			// 输出文件上传最终的路径 测试查看
			List<User> users = UploadUserUtils.getUsers(path + File.separator + filename,"/t");
			List<User> newUsers = new ArrayList<User>();
			List<User> exitsUser = new ArrayList<User>();

			for (Iterator iterator = users.iterator(); iterator.hasNext();) {
				User user = (User) iterator.next();
				User user1 = this.userService.findByName(user.getUsername());
				if(user1==null){
					newUsers.add(user);
				}else{
					exitsUser.add(user1);
				}
			}
			this.userService.batchSave(newUsers);
			model.addAttribute("newUsers", newUsers);
			model.addAttribute("exitsUser", exitsUser);
		}

		return "system/user/uploadSuccess";
	}

}
