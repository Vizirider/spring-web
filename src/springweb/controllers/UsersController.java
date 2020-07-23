package springweb.controllers;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springweb.dao.User;
import springweb.service.OffersService;
import springweb.service.UsersService;

@Controller
public class UsersController {
	
	private UsersService usersservice;
	
	@Autowired
	public void setUsersService(UsersService usersservice) {
		this.usersservice = usersservice;
	}
	
	@RequestMapping(value="/testuser", method=RequestMethod.GET)
	public String showTest(Model model, @RequestParam("name") String name) {
		System.out.println("name is: " + name);
		return "home";
	}
/*
	@RequestMapping("/users")
	public String showUsers(Model model) {
		
		List<User> user = usersservice.getCurrent();
		
		model.addAttribute("users", user);
		
		return "users";
	}
*/	
	@RequestMapping("/createuser")
	public String createUser(Model model) {
	
		model.addAttribute("user", new User());
		
		return "createuser";
	}
	
	@RequestMapping(value="/createaccount", method=RequestMethod.POST)
	public String doCreate(User user, BindingResult result) {
		
		if(result.hasErrors()) {
			return "createuser";
		}
		user.setAuthority("user");
		user.setEnabled(true);
		usersservice.create(user);
		
		return "accountcreated";
	}
}
