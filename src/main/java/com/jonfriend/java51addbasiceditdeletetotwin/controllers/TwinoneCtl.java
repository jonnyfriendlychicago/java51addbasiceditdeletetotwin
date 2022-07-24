package com.jonfriend.java51addbasiceditdeletetotwin.controllers;

//import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jonfriend.java51addbasiceditdeletetotwin.models.TwintwoMdl;
import com.jonfriend.java51addbasiceditdeletetotwin.models.TwinoneMdl;
import com.jonfriend.java51addbasiceditdeletetotwin.services.TwintwoSrv;
import com.jonfriend.java51addbasiceditdeletetotwin.services.TwinoneSrv;
import com.jonfriend.java51addbasiceditdeletetotwin.services.UserSrv;

@Controller
public class TwinoneCtl {

	@Autowired
	private TwinoneSrv twinoneSrv;
	
	@Autowired
	private TwintwoSrv twintwoSrv;
	
	@Autowired
	private UserSrv userSrv;
	
	// display create-new twinone page
//	@GetMapping("/store/twinone/new")
	@GetMapping("/twinone/new")
	public String newTwinone(
			@ModelAttribute("twinone") TwinoneMdl twinoneMdl
			, Model model
			, HttpSession session
			) {
		
		// If no userId is found in session, redirect to logout.  JRF: put this on basically all methods now, except the login/reg pages
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		
		// We get the userId from our session (we need to cast the result to a Long as the 'session.getAttribute("userId")' returns an object
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userSrv.findById(userId));
		
//		return "store/twinone/create.jsp";
		return "twinone/create.jsp";
	}
	
	// process the create-new twinone 
//	@PostMapping("/store/twinone/new")
	@PostMapping("/twinone/new")
	public String addNewTwinone(
			@Valid @ModelAttribute("twinone") TwinoneMdl twinoneMdl
			, BindingResult result
			, Model model
			, HttpSession session
			) {
		
		// If no userId is found in session, redirect to logout.  JRF: put this on basically all methods now, except the login/reg pages
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		
		// We get the userId from our session (we need to cast the result to a Long as the 'session.getAttribute("userId")' returns an object
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userSrv.findById(userId));
		
		if(result.hasErrors()) {
//			return "store/twinone/create.jsp";
			return "twinone/create.jsp";
		}else {
			twinoneSrv.addTwinone(twinoneMdl);
//			return "redirect:/store";
			return "redirect:/home";
		}
	}
	
	// view one twinone
//	@GetMapping("/store/twinone/{id}")
	@GetMapping("/twinone/{id}")
	public String showTwinone(
			@PathVariable("id") Long id
			, Model model
			, HttpSession session
			) {
		
		// If no userId is found in session, redirect to logout.  JRF: put this on basically all methods now, except the login/reg pages
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		
		// We get the userId from our session (we need to cast the result to a Long as the 'session.getAttribute("userId")' returns an object
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userSrv.findById(userId));
		
		TwinoneMdl intVar = twinoneSrv.findById(id);
		
		model.addAttribute("twinone", intVar);
		model.addAttribute("assignedCategories", twintwoSrv.getAssignedTwinones(intVar));
		model.addAttribute("unassignedCategories", twintwoSrv.getUnassignedTwinones(intVar));
		
//		return "/store/twinone/record.jsp";
		return "twinone/record.jsp";
	}
	
	// render edit twinone page
	@GetMapping("/twinone/{id}/edit")
	public String editTwinone(
			@PathVariable("id") Long twinoneId
			, Model model
			, HttpSession session
			) {
		
		// If no userId is found in session, redirect to logout.  JRF: put this on basically all methods now, except the login/reg pages
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}

		// We get the userId from our session (we need to cast the result to a Long as the 'session.getAttribute("userId")' returns an object
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userSrv.findById(userId));
		
		// pre-populates the values in the management interface
		TwinoneMdl intVar = twinoneSrv.findById(twinoneId);
		
		model.addAttribute("twinone", intVar);
		model.addAttribute("assignedCategories", twintwoSrv.getAssignedTwinones(intVar));
		model.addAttribute("unassignedCategories", twintwoSrv.getUnassignedTwinones(intVar));
		
		// records in 'manage-one' interface dropdown
//		List<DojoMdl> intVar3 = dojoSrvIntVar.returnAll();
//		model.addAttribute("dojoList", intVar3); 
		
		return "twinone/edit.jsp";
	}
	
	// edit record: finalize/save it (or get kicked back b/c errors)
	@PostMapping("/twinone/{id}/edit")
	public String PostTheEditTwinone(
			@Valid 
			@ModelAttribute("twinone") TwinoneMdl twinoneMdl 
			, BindingResult result
			, Model model
			, @PathVariable("id") Long twinoneId // out CadenJon
			, HttpSession session
			, RedirectAttributes redirectAttributes
			) {
		
		// If no userId is found in session, redirect to logout.  JRF: put this on basically all methods now, except the login/reg pages
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		
		// trying here to stop someone from forcing this method when not creator; was working, now no idea.... sigh 7/19 2pm
		// Long userId = (Long) session.getAttribute("userId"); 
		// PublicationMdl intVar = twinoneSrv.findById(twinoneId);
		
		// System.out.println("in the postMapping for edit..."); 
		// System.out.println("intVar.getUserMdl().getId(): " + intVar.getUserMdl().getId()); 
		// System.out.println("userId: " + userId); 
		
//		if(intVar.getUserMdl().getId() != userId) {
//			redirectAttributes.addFlashAttribute("mgmtPermissionErrorMsg", "Only the creator of a record can edit it.");
//			return "redirect:/publication";
//		}
		TwinoneMdl intVar = twinoneSrv.findById(twinoneId);
		
		if (result.hasErrors()) { 
			
			System.out.println("damn, we in hasErrors flow");
			
			System.out.println("result.rejectValuestuff: " + result.getAllErrors().toString());
			
			
            Long userId = (Long) session.getAttribute("userId");
            model.addAttribute("user", userSrv.findById(userId));
            
            // pre-populates the values in the management interface
            
//            model.addAttribute("twinone", intVar);
            model.addAttribute("assignedCategories", twintwoSrv.getAssignedTwinones(intVar));
            model.addAttribute("unassignedCategories", twintwoSrv.getUnassignedTwinones(intVar));

			return "twinone/edit.jsp";
		} else {
			
			twinoneMdl.setTwintwoMdl(twintwoSrv.getAssignedTwinones(intVar)); 
			twinoneSrv.update(twinoneMdl);
			
			return "redirect:/twinone/" + twinoneId;
		}
	}
	
	// process new joins for that one twinone
//	@PostMapping("/store/twinone/{id}")
	@PostMapping("/twinone/{id}")
//	public String editTwinone(
	public String postTwinoneTwintwoJoin(
			@PathVariable("id") Long id
			, @RequestParam(value="twintwoId") Long catId // requestParam is only used with regular HTML form 
			,  Model model
			, HttpSession session
			) {
		
		// If no userId is found in session, redirect to logout.  JRF: put this on basically all methods now, except the login/reg pages
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		
		// We get the userId from our session (we need to cast the result to a Long as the 'session.getAttribute("userId")' returns an object
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userSrv.findById(userId));
		
		TwinoneMdl twinone = twinoneSrv.findById(id);
		TwintwoMdl twintwo = twintwoSrv.findById(catId);
		
		twinone.getTwintwoMdl().add(twintwo);
		
		twinoneSrv.update(twinone);
		
		model.addAttribute("assignedCategories", twintwoSrv.getAssignedTwinones(twinone));
		model.addAttribute("unassignedCategories", twintwoSrv.getUnassignedTwinones(twinone));
//		return "redirect:/store/twinone/" + id;
//		return "redirect:/twinone/" + id;
		return "redirect:/twinone/" + id + "/edit";
	}
	
	// this is JRF method to remove instance of a cat-pro join record from the twinone. 
	// don't use deleteMapping here... just use a link on the page to call the 'remove' method to remove item from list, something like that. 
	
	
	
//    @DeleteMapping("/store/removeTwinoneTwintwoJoin")
	@DeleteMapping("/removeTwinoneTwintwoJoin")
    public String whackTwinoneTwintwoJoin(
//    		@PathVariable("publicationId") Long publicationId
    		@RequestParam(value="twintwoId") Long twintwoId // requestParam is only used with regular HTML form
    		, @RequestParam(value="twinoneId") Long twinoneId // requestParam is only used with regular HTML form
    		, @RequestParam(value="origin") Long originPath // requestParam is only used with regular HTML form
    		, HttpSession session
    		, RedirectAttributes redirectAttributes
    		) {

    	// If no userId is found in session, redirect to logout.  JRF: put this on basically all methods now, except the login/reg pages
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		
//		here is the srv to remove this thing
//		but first need to get the some thing via id's coming from param
		
		TwinoneMdl twinoneObject = twinoneSrv.findById(twinoneId);
		TwintwoMdl twintwoObject  = twintwoSrv.findById(twintwoId);
		
		twinoneSrv.removeTwinoneTwintwoJoin(twintwoObject, twinoneObject); 
		
		
		if (originPath == 1) {
//			return "redirect:/store/twinone/" + twinoneId;
//			return "redirect:/twinone/" + twinoneId;
			return "redirect:/twinone/" + twinoneId + "/edit";
		} else {
//			return "redirect:/store/twintwo/" + twintwoId;
			return "redirect:/twintwo/" + twintwoId;
		}
    }
	
	

	
	
// end of ctl
}
