package com.example.Assignment2.Configuration;

import com.example.Assignment2.Services.TokenService;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class JwtFilter extends GenericFilterBean {

    private final TokenService tokenService;

    JwtFilter(TokenService tokenService)
    {
        this.tokenService= tokenService;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // Casting servlet requests and responses to HTTP-specific types

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;


        // Retrieving the token from the Authorization header of the HTTP request


        String token = httpServletRequest.getHeader("Authorization");

        // Handling preflight requests (OPTIONS) and sending a success response

        if ("OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod()))
        {
            httpServletResponse.sendError(HttpServletResponse.SC_OK, "Success");
            return;
        }
        if (allowReqWithoutToken(httpServletRequest))
        {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK, "Success");
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
        else
        {
            ObjectId userId = new ObjectId(tokenService.getUserToken(token));
            httpServletRequest.setAttribute("userId",userId);
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    // Method to check if a request is allowed without a token based on the URI containing "admin"

    public boolean allowReqWithoutToken(HttpServletRequest httpServletRequest){
        if (httpServletRequest.getRequestURI().contains("admin"))
        {
            return  true;
        }
        return  false;
    }
}
