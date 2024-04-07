package com.ArtSeeReal.pro.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@Hidden // todo Swagger에 안 뜨는지 확인
public class AdminController {

    @GetMapping("/admin")
    public String adminPage() {

        return "admin Controller";
    }
}