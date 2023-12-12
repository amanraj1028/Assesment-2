package com.example.Assignment2.Services;

import com.example.Assignment2.Model.Admin;
import com.example.Assignment2.Repository.AdminRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
        private AdminRepository adminRepository;
        private TokenService tokenService;


        AdminService(AdminRepository userRepository, TokenService tokenService)
        {
            this.adminRepository = userRepository;
            this.tokenService = tokenService;
        }


        public Admin getAdmin(ObjectId id)
        {
            Optional<Admin> optionalUser = adminRepository.findById(id);
            return optionalUser.orElseGet(optionalUser::get);
        }

        public List<Admin> getAdmin(){
            return adminRepository.findAll();
        }

        public String signUp(Admin admin){
            Admin savedUser = adminRepository.save(admin);
            return String.format( "{\n\t \"Message\": \"Successfully Create the user\",\n\t \"data\": %s \n}", admin.toString());
        }


        public String login(String email, String password)
        {
            List<Admin> foundUsers = adminRepository.getAdminByEmail(email);
            if (foundUsers.isEmpty())
            {
                return "Authentication failed";
            }
            else if(!foundUsers.get(0).getPassword().equals(password))
            {
                return "Incorrect Password";
            }

            String token = tokenService.createToken(foundUsers.get(0).getId());

            return String.format("{\12\t\"message\": \"Successfully logged in\",\12\t" +
                            "\"data\":{\"Name\": \"%s\", \n\t\t \"Email\": \"%s\" },\12\t\"token\": \"%s\" \n }", foundUsers.get(0).getName(),
                    foundUsers.get(0).getEmail(),token);
        }
    }


