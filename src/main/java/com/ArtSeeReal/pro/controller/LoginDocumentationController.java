//package com.ArtSeeReal.pro.controller;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class LoginDocumentationController {
//
//    @PostMapping("/login")
//    @Operation(summary = "User Login", description = "Login with username and password")
//    @RequestBody(
//            required = true,
//            content = @Content(
//                    schema = @Schema(
//                            description = "Login Request",
//                            required = true,
//                            implementation = LoginRequest.class
//                    )
//            )
//    )
//    public void fakeLogin() {
//        // 이 메소드는 실제로 실행되지 않습니다. 스웨거 문서용입니다.
//        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
//    }
//}
//
//// 별도의 클래스로 LoginRequest DTO 정의
//class LoginRequest {
//    @Schema(description = "User's username", example = "user123", required = true)
//    private String username;
//
//    @Schema(description = "User's password", example = "pass#123", required = true)
//    private String password;
//
//    // standard getters and setters
//}