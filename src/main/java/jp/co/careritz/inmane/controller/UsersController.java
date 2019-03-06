package jp.co.careritz.inmane.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import jp.co.careritz.inmane.config.PropertyConfig;
import jp.co.careritz.inmane.controller.commons.AbstractAppController;
import jp.co.careritz.inmane.dto.UsersDto;
import jp.co.careritz.inmane.form.UsersCreateForm;
import jp.co.careritz.inmane.form.UsersSearchForm;
import jp.co.careritz.inmane.model.security.SecurityUserModel;
import jp.co.careritz.inmane.service.UsersService;

@Controller
@RequestMapping("maintenance/users")
public class UsersController extends AbstractAppController {
	
	// ----------------------------------------------------------------------
	// インスタンス変数
	// ----------------------------------------------------------------------
	@Autowired
	private PropertyConfig propertyConfig;
	@Autowired
	UsersService usersService;
	
	// ----------------------------------------------------------------------
	// インスタンスメソッド
	// ----------------------------------------------------------------------
	@GetMapping("search")
	public String viewSearch(
			@ModelAttribute UsersSearchForm form,
			Model model) {
		System.out.println("### userid:" + form.getUserid());
		System.out.println("### username:" + form.getUsername());
		System.out.println("### roleName:" + form.getRoleName());
		System.out.println("### nonDeleted:" + form.getNonDeleted());

		UsersDto dto = new UsersDto();
		
		dto.setUserid(form.getUserid());
		dto.setUsername(form.getUsername());
		dto.setRoleName("ALL".equals(form.getRoleName()) ? "" : form.getRoleName());
		
		List<UsersDto> usersList = usersService.find(dto, "true".equals(form.getNonDeleted()));
		
		// 検索条件を設定
		model.addAttribute("usersSearchForm", form);
		// 検索結果を設定
		model.addAttribute("usersList", usersList);

		return "users_search";
	}
	
	@GetMapping("new")
	public String viewCreate(Model model) {
		
		UsersCreateForm form = new UsersCreateForm();
		form.setRoleName("USER");
		
		model.addAttribute("usersCreateForm", form);
		
		return "users_create";
	}
	
	@PostMapping("new")
	public String create(Model model, 
			@ModelAttribute @Valid UsersCreateForm form,
			BindingResult bindingResult,
			@AuthenticationPrincipal SecurityUserModel userDetails,
			RedirectAttributes redirectAttributes,
			HttpServletRequest req,
			HttpServletResponse res) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("usersCreateForm", form);
			return "users_create";
		}
		
		final String prefix = propertyConfig.get("user.defaultpass.prefix");
		final String password = new BCryptPasswordEncoder().encode(prefix + form.getUserid());
		
		UsersDto dto = new UsersDto();
		
		dto.setUserid(form.getUserid());
		dto.setPassword(password);
		dto.setUsername(form.getUsername());
		dto.setRoleName(form.getRoleName());
		dto.setDeleted(0);
		dto.setCreaterId(userDetails.getUserid());
		
		int result = usersService.create(dto);
		
		FlashMap flashMap = RequestContextUtils.getOutputFlashMap(req);
		FlashMapManager flashMapManager = new SessionFlashMapManager();
		
		if (result == 0) {
			String msg = propertyConfig.get("ok.app.complete");
			// Flashスコープで画面に表示するエラーメッセージを設定
			flashMap.put("appCompleteMessage", msg);
			flashMapManager.saveOutputFlashMap(flashMap, req, res);
		} else {
			String errMsg = (result == 1) ? propertyConfig.get("error.app.user.deplicated") : propertyConfig.get("error.app.fatal");
			// Flashスコープで画面に表示するエラーメッセージを設定
			flashMap.put("createFailureMessage", errMsg);
			flashMapManager.saveOutputFlashMap(flashMap, req, res);
			
			model.addAttribute("usersCreateForm", form);
			return "users_create";
		}
		return "redirect:/maintenance/users/detail?userid=" + dto.getUserid();
	}
	
	@GetMapping("detail")
	public String viewDetail(Model model, @RequestParam(name = "userid", defaultValue = "") String userid) {
		
		if ("".equals(userid)) {
			return "redirect:/error/403";
		}
		
		UsersDto dto = usersService.findByPk(userid);
		
		if (dto == null) {
			return "redirect:/error/403";
		}
		UsersCreateForm form = new UsersCreateForm();
		form.setUserid(dto.getUserid());
		form.setUsername(dto.getUsername());
		form.setRoleName(dto.getRoleName());
		form.setLoginFailureCount(dto.getLoginFailureCount());
		form.setLoginDenyTime(dto.getLoginDenyTime());
		form.setDeleted(dto.getDeleted());
		form.setUpdaterId(dto.getUpdaterId());
		form.setUpdateTime(dto.getUpdateTime());
		form.setCreaterId(dto.getCreaterId());
		form.setCreateTime(dto.getCreateTime());
		
		model.addAttribute("usersCreateForm", form);
		
		return "users_create";
	}
	
	
	@GetMapping("edit")
	public String viewEdit(Model model, @RequestParam(name = "userid", defaultValue = "") String userid) {
		
		if ("".equals(userid)) {
			return "redirect:/error/403";
		}
		
		UsersDto dto = usersService.findByPk(userid);
		
		if (dto == null) {
			return "redirect:/error/403";
		}
		
		UsersCreateForm form = new UsersCreateForm();
		form.setUserid(dto.getUserid());
		form.setUsername(dto.getUsername());
		form.setRoleName(dto.getRoleName());
		form.setLoginFailureCount(dto.getLoginFailureCount());
		form.setLoginDenyTime(dto.getLoginDenyTime());
		form.setDeleted(dto.getDeleted());
		form.setUpdaterId(dto.getUpdaterId());
		form.setUpdateTime(dto.getUpdateTime());
		form.setCreaterId(dto.getCreaterId());
		form.setCreateTime(dto.getCreateTime());
		
		model.addAttribute("usersCreateForm", form);
		
		return "users_create";
	}
	
	@PostMapping("edit")
	public String update(Model model, 
			@ModelAttribute @Valid UsersCreateForm form,
			BindingResult bindingResult,
			@AuthenticationPrincipal SecurityUserModel userDetails,
			RedirectAttributes redirectAttributes,
			HttpServletRequest req,
			HttpServletResponse res) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("usersCreateForm", form);
			return "users_create";
		}
		
		final String password = new BCryptPasswordEncoder().encode(form.getPassword());
		
		UsersDto dto = new UsersDto();
		
		dto.setUserid(form.getUserid());
		dto.setPassword(password);
		dto.setUsername(form.getUsername());
		dto.setRoleName(form.getRoleName());
		dto.setDeleted(0);
		dto.setUpdaterId(userDetails.getUserid());
		
		int result = usersService.updateByPk(dto);
		
		FlashMap flashMap = RequestContextUtils.getOutputFlashMap(req);
		FlashMapManager flashMapManager = new SessionFlashMapManager();
		
		if (result == 0) {
			String msg = propertyConfig.get("ok.app.complete");
			// Flashスコープで画面に表示するエラーメッセージを設定
			flashMap.put("appCompleteMessage", msg);
			flashMapManager.saveOutputFlashMap(flashMap, req, res);
		} else {
			String errMsg = (result == 1) ? propertyConfig.get("error.app.user.deplicated") : propertyConfig.get("error.app.fatal");
			// Flashスコープで画面に表示するエラーメッセージを設定
			flashMap.put("createFailureMessage", errMsg);
			flashMapManager.saveOutputFlashMap(flashMap, req, res);
			
			model.addAttribute("usersCreateForm", form);
			return "users_create";
		}
		return "redirect:/maintenance/users/detail?userid=" + dto.getUserid();
	}
}