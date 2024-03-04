package com.animal.PageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/index")
	public String getAdminIndexPage() {
		return "admin/index";
	}
	@GetMapping("/login")
	public String geUserLoginPage() {
		return "login";
	}

	@GetMapping("/admin-login")
	public String getAdminLoginPage() {
		return "admin/login";
	}

	@GetMapping("/admin-register")
	public String getAdminRegisterPage() {
		return "admin/register";
	}
	@GetMapping("/register")
	public String getRegisterPage() {
		return "register";
	}


	@GetMapping("/add-images")
	public String getAddImagesPage() {
		return "admin/add-images";
	}

	@GetMapping("/point-ratio")
	public String getPointRatioPage() {
		return "admin/point-ratio";
	}

	@GetMapping("/manage-users")
	public String getManageUsersPage() {
		return "admin/manage-users";
	}
	@GetMapping("/plans")
	public String getPlansPage() {
		return "admin/plans";
	}
	
	@GetMapping("/manage-admin")
	public String getManageAdminsPage() {
		return "admin/manage-admins";
	}
	
	
	
	@GetMapping("/user-index")
	public String userIndexPage()
	{
		return "index";
	}

	@GetMapping("/payment")
	public String getPaymentPage()
	{
		return "payment";
	}

}
