package jp.co.careritz.inmane.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.careritz.inmane.controller.commons.AbstractAppController;

@Controller
public class ClientController extends AbstractAppController {
	@RequestMapping(value = "client", method = RequestMethod.GET)
	String showStaffList(Model model) {
		return "client_search";
	}
}