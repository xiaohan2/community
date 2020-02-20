package com.cyh.community.controller;

import com.cyh.community.dao.AccessTokenDAO;
import com.cyh.community.dao.GithubUser;
import com.cyh.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDAO accessTokenDAO = new AccessTokenDAO();
        accessTokenDAO.setClient_id("c8e4fe320007882d855c");
        accessTokenDAO.setClient_secret("73c9c26cae827e813067c7148a79c5c5066d5646");
        accessTokenDAO.setCode(code);
        accessTokenDAO.setRedirect_uri("http://localhost:8088/callback");
        accessTokenDAO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDAO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        System.out.println(user.getId());
        System.out.println(user.getBio());
        return "index";
    }
}
