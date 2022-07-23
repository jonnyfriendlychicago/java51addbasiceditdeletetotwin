package com.jonfriend.java51addbasiceditdeletetotwin.controllers;

//import java.util.List;

//import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jonfriend.java51addbasiceditdeletetotwin.models.TwintwoMdl;
import com.jonfriend.java51addbasiceditdeletetotwin.models.TwinoneMdl;
import com.jonfriend.java51addbasiceditdeletetotwin.services.TwintwoSrv;
import com.jonfriend.java51addbasiceditdeletetotwin.services.TwinoneSrv;
//import com.jonfriend.java51addbasiceditdeletetotwin.services.UserSrv;

@Controller
public class TwintwoCtl {

	@Autowired
	private TwinoneSrv twinoneSrv;
	
	@Autowired
	private TwintwoSrv twintwoSrv;
	
//	@Autowired
//	private UserSrv userSrv;
	
	// display create-new twintwo page
//	@GetMapping("/store/twintwo/new")
	@GetMapping("/twintwo/new")
	public String newTwintwo(
			@ModelAttribute("twintwo") TwintwoMdl twintwoMdl
			) {
//		return "store/twintwo/create.jsp";}
		return "twintwo/create.jsp";}
	
	
	// process the create-new twintwo
//	@PostMapping("/store/twintwo/new")
	@PostMapping("/twintwo/new")
	public String addNewTwintwo(
			@Valid @ModelAttribute("twintwo") TwintwoMdl twintwoMdl
			, BindingResult result
			, Model model) {
		
		if(result.hasErrors()) {
//			return "store/twintwo/create.jsp";
			return "twintwo/create.jsp";
		} else {
			twintwoSrv.addTwintwo(twintwoMdl);
//			return "redirect:/store";
			return "redirect:/home";
		}	
	}
	
	// view/manage one twintwo
//	@GetMapping("/store/twintwo/{id}")
	@GetMapping("/twintwo/{id}")
	public String showTwintwo(
			@PathVariable("id") Long id
			, Model model
			) {
		TwintwoMdl twintwo = twintwoSrv.findById(id);
		
		model.addAttribute("twintwo", twintwoSrv.findById(id));
		model.addAttribute("assignedTwinones", twinoneSrv.getAssignedCategories(twintwo));
		model.addAttribute("unassignedTwinones", twinoneSrv.getUnassignedCategories(twintwo));
		
//		return "/store/twintwo/record.jsp";
		return "/twintwo/record.jsp";
	}
	
	// process edits to that one twintwo
//	@PostMapping("/store/twintwo/{id}")
	@PostMapping("/twintwo/{id}")
	
	public String editTwintwo(
			@PathVariable("id") Long id
			, @RequestParam(value="twinoneId") Long twinoneId
			, Model model
			) {
		
		TwintwoMdl twintwo = twintwoSrv.findById(id);
		TwinoneMdl twinone = twinoneSrv.findById(twinoneId);
		
		twintwo.getTwinoneMdl().add(twinone);
		twintwoSrv.updateTwintwo(twintwo);
		model.addAttribute("assignedTwinones", twinoneSrv.getAssignedCategories(twintwo));
		model.addAttribute("unassignedTwinones", twinoneSrv.getUnassignedCategories(twintwo));
//		return "redirect:/store/twintwo/" + id;
		return "redirect:/twintwo/" + id;
		
	}

// end ctrl
}
