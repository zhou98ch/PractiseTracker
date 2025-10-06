package com.example.server.controller;

import Utils.JwtUtil;
import com.example.pojo.DTO.UserDTO;
import com.example.pojo.DTO.UserLoginDTO;
import com.example.pojo.VO.UserLoginVO;
import com.example.pojo.entity.User;
import com.example.result.Result;
import com.example.server.service.UserService;
import constant.JwtClaimsConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import properties.JwtProperties;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * login
     *
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {

        User user = userService.login(userLoginDTO);

        //after login, generate jwt token
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .token(token)
                .build();

        //return jwt token to frontend
        return Result.success(userLoginVO);
    }
    @PostMapping("/create")
    @CrossOrigin(origins = "*",
            allowedHeaders = "*",
            exposedHeaders = "*")  // Add this line
    public Result save(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
        return Result.success();
    }
}
