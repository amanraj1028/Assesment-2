package com.example.Assignment2.Controller;

import com.example.Assignment2.Model.Admin;
import com.example.Assignment2.Services.AdminService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    AdminController(AdminService adminService)
    {
        this.adminService = adminService;
    }

    // Endpoint for registering a new admin using HTTP POST method

    @PostMapping("/add-admin")
    public String signUp(@RequestBody Admin admin)
    {
        return adminService.signUp(admin);
    }

    // Endpoint for admin login using HTTP POST method

    @PostMapping("/login")
    public String login(@RequestBody Map<String,Object> map)
    {
        return adminService.login(map.get("email").toString(), map.get("password").toString());
    }

    // Endpoint to retrieve admin details using HTTP GET method

    @GetMapping("/get-admin")
    public Admin getAdmin(HttpServletRequest request)
    {
        ObjectId userId = (ObjectId) request.getAttribute("userId");
        return adminService.getAdmin(userId);

    }
}
