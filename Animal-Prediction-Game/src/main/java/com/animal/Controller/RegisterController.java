package com.animal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.animal.Entity.Register;
import com.animal.ServiceImpl.RegisterImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class RegisterController {
	
	@Autowired
	private RegisterImpl imp;
	
	public RegisterController(RegisterImpl imp) {
		super();
		this.imp = imp;
	}
	
	
	@PostMapping("/userRegistration")
	@ResponseBody
	public ResponseEntity<Register> saveUserData(@RequestBody Register register)
	{
		Register reg=imp.saveUser(register);
		return new ResponseEntity<>(reg,HttpStatus.CREATED);
	}
	@PostMapping("/userLogin")
	@ResponseBody
	public ResponseEntity<String> loginUser(@RequestBody Register loginRequest, HttpSession session) {
	    Register user = imp.getUserData(loginRequest.getEmail(), loginRequest.getPassword());

	    if (user != null) {
	        // Successful login
	    	session.setAttribute("id", user.getId());
	    	session.setAttribute("email", user.getEmail());
	        return ResponseEntity.ok("Login successful!");
	    } else {
	        // Failed login
	        return ResponseEntity.status(401).body("Invalid credentials");
	    }
	}
	@GetMapping("/checklog")
	@ResponseBody
	public String checkLogin(HttpSession session) {
	    Long userId = (Long) session.getAttribute("id");

	    if (userId != null) {
	        // Retrieve the Register object using the user's ID
	        Register user = imp.getUserById(userId);

	        if (user != null) {
	            // User is logged in
	            return "loggedIn";
	        }
	    }

	    // User is not logged in
	    return "notLoggedIn";
	}
	

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Invalidate the session
        session.invalidate();
        // Redirect to the login page or any other desired page after logout
        return "redirect:/login";
    }
   
    @GetMapping("/profile")
    @ResponseBody
    public ResponseEntity<Register> getProfileData(HttpSession session) {
        Long userId = (Long) session.getAttribute("id");

        if (userId != null) {
            Register user = imp.getUserById(userId);

            if (user != null) {
                // Return user profile data if found
                return ResponseEntity.ok(user);
            }
        }

        // If user is not logged in or profile data not found, return 404 Not Found
        return ResponseEntity.status(404).build();
    }
    
    
    @PutMapping("/updateProfile")
    @ResponseBody
    public ResponseEntity<Register> updateProfile(@RequestBody Register updatedUser, HttpSession session) {
        Long userId = (Long) session.getAttribute("id");

        if (userId != null) {
            Register existingUser = imp.getUserById(userId);

            if (existingUser != null) {
                // Update existing user fields
                existingUser.setName(updatedUser.getName());
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setMobileno(updatedUser.getMobileno());
                existingUser.setPassword(updatedUser.getPassword());

                // Save the updated user
                Register savedUser = imp.saveUser(existingUser);
                return ResponseEntity.ok(savedUser);
            } else {
                // User with the session ID not found
                return ResponseEntity.notFound().build();
            }
        } else {
            // User ID not found in the session
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

  

}
