package com.digi.bxmd.controller

import com.digi.bxmd.dto.JwtToken
import com.digi.bxmd.dto.ResponseDto
import com.digi.bxmd.dto.SignInDto
import com.digi.bxmd.dto.UserDto
import com.digi.bxmd.service.UserService
import com.digi.bxmd.service.impl.AuthServiceImpl
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("auth")
@Api(tags = ["Authentication"], description = "Authenticate users")
class AuthController @Autowired constructor(
    private val authServiceImpl: AuthServiceImpl,
    private val userServiceImpl: UserService,
//    private val authHelper: AuthHelperΩ
) {

    @PostMapping("/signIn")
    @ApiOperation("Sign in by email and password")
    fun signIn(@Valid @RequestBody signInDto: SignInDto): JwtToken {
        return authServiceImpl.signIn(signInDto)
    }

    @PostMapping("/signOut")
    @ApiOperation(value = "Sign out")
    fun signOut(@Valid @RequestBody jwtToken: JwtToken) {
        authServiceImpl.signOut(jwtToken)
    }

    @PostMapping("/refresh")
    @ApiOperation(value = "Refresh")
    fun refresh(@Valid @RequestBody jwtToken: JwtToken): JwtToken {
        return authServiceImpl.refreshToken(jwtToken)
    }

    @GetMapping("/me")
    @ApiOperation(value = "Get current user")
    fun getCurrentUser(): ResponseEntity<ResponseDto> {
        return ResponseDto.ok(userServiceImpl.getCurrentUserDto())
    }

}
