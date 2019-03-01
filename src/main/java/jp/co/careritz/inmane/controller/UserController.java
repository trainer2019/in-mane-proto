package jp.co.careritz.inmane.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.careritz.inmane.controller.commons.AbstractAppController;
import jp.co.careritz.inmane.dto.UserDto;
import jp.co.careritz.inmane.form.UserSearchForm;
import jp.co.careritz.inmane.service.UserService;

@Controller
public class UserController extends AbstractAppController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "maintenance/user", method = RequestMethod.GET)
	public String search(
			@RequestParam(name = "userid", defaultValue = "") String userid,
			@RequestParam(name = "username", defaultValue = "") String username,
			@RequestParam(name = "roleName", defaultValue = "") String roleName,
			@RequestParam(name = "deleted", defaultValue = "") String deleted,
			Model model) {
		System.out.println("### userid:" + userid);
		System.out.println("### username:" + username);
		System.out.println("### roleName:" + roleName);
		System.out.println("### deleted:" + deleted);

		List<UserDto> users = userService.find(userid, username, roleName, deleted);
		
		UserSearchForm form = new UserSearchForm();
		form.setUserid(userid);
		form.setUsername(username);
		form.setRoleName(roleName);
		form.setDeleted(deleted);
		
		model.addAttribute("users", users);
		model.addAttribute("userSearchForm", form);

		return "user_search";
	}
}