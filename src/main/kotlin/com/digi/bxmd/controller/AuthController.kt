package com.digi.bxmd.controller

import com.digi.bxmd.dto.JwtToken
import com.digi.bxmd.dto.LoginDto
import com.digi.bxmd.dto.ResponseDto
import com.digi.bxmd.service.UserService
import com.digi.bxmd.service.impl.AuthServiceImpl
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("\${apiBasePath}/auth")
@Api(tags = ["Authentication"], description = "Authenticate users")
class AuthController @Autowired constructor(
    private val authService: AuthServiceImpl,
    private val userService: UserService,
//    private val authHelper: AuthHelperΩ
) {

    @PostMapping("/login")
    @ApiOperation("Sign in by email and password")
    fun login(@Valid @RequestBody loginDto: LoginDto): JwtToken {
        return authService.login(loginDto)
    }

    @PostMapping("/logout")
    @ApiOperation(value = "Sign out")
    fun logout(@Valid @RequestBody jwtToken: JwtToken) {
        authService.logout(jwtToken)
    }

    @PostMapping("/refresh")
    @ApiOperation(value = "Refresh")
    fun refresh(@Valid @RequestBody jwtToken: JwtToken): JwtToken {
        return authService.refreshToken(jwtToken)
    }

    @GetMapping("/me")
    @ApiOperation(value = "Get current user")
    fun getCurrentUser(): ResponseEntity<ResponseDto> {
        return ResponseDto.ok(userService.getCurrentUserDto())
    }

}
