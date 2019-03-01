package jp.co.careritz.inmane.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.careritz.inmane.controller.commons.AbstractAppController;
import jp.co.careritz.inmane.dto.UserDto;
import jp.co.careritz.inmane.form.UserCreateForm;
import jp.co.careritz.inmane.form.UserSearchForm;
import jp.co.careritz.inmane.service.UserService;

@Controller
@RequestMapping("maintenance/user")
public class UserController extends AbstractAppController {
	@Autowired
	UserService userService;
	
	@GetMapping("top")
	public String top(Model model) {
		String userid = "";
		String username = "";
		String roleName = "";
		String nonDeleted = "true";
		
		return this.search(userid, username, roleName, nonDeleted, model);
	}
	
	@GetMapping("search")
	public String search(
			@RequestParam(name = "userid", defaultValue = "") String userid,
			@RequestParam(name = "username", defaultValue = "") String username,
			@RequestParam(name = "roleName", defaultValue = "") String roleName,
			@RequestParam(name = "nonDeleted", defaultValue = "") String nonDeleted,
			Model model) {
		System.out.println("### userid:" + userid);
		System.out.println("### username:" + username);
		System.out.println("### roleName:" + roleName);
		System.out.println("### nonDeleted:" + nonDeleted);

		List<UserDto> users = userService.find(userid, username, roleName, nonDeleted);
		
		// 検索条件を設定
		UserSearchForm form = new UserSearchForm();
		form.setUserid(userid);
		form.setUsername(username);
		form.setRoleName(roleName);
		form.setNonDeleted(nonDeleted);
		
		model.addAttribute("userSearchForm", form);
		// 検索結果を設定
		model.addAttribute("users", users);

		//return "user_top";
		return "user_search";
	}
	
	@GetMapping("edit/new")
	public String createUser(Model model) {
		
		UserCreateForm form = new UserCreateForm();
		
		model.addAttribute("userCreateForm", form);
		
		return "user_create";
	}
}