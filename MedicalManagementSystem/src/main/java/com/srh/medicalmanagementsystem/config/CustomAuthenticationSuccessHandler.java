package com.srh.medicalmanagementsystem.config;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        User user = (User) authentication.getPrincipal();

        System.out.println("EmployeeID: "+user.getUsername());
        request.getSession().setAttribute("employeeID", user.getUsername());

        String role = authentication.getAuthorities().iterator().next().getAuthority();

        System.out.println("Role "+role);

        if (role.equals("ROLE_PATIENT")) {
            response.sendRedirect("/patients/details/"+user.getUsername());
        } else {
            response.sendRedirect("/medicalmanagement/dashboard");
        }
       // response.sendRedirect("/medicalmanagement/dashboard");
    }
}