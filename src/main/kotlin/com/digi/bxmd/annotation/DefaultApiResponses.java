package com.digi.bxmd.annotation;

import com.digi.bxmd.constant.HttpStatusConstant;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {@ApiResponse(code = 200, message = HttpStatusConstant.S200_OK_SUCCESS),
        @ApiResponse(code = 401, message = HttpStatusConstant.S401_UNAUTHORIZED),
        @ApiResponse(code = 404, message = HttpStatusConstant.S404_NOT_FOUND),
        @ApiResponse(code = 405, message = HttpStatusConstant.S405_METHOD_NOT_ALLOWED),
        @ApiResponse(code = 406, message = HttpStatusConstant.S406_NOT_ACCEPTABLE),
        @ApiResponse(code = 500, message = HttpStatusConstant.S500_INTERNAL_SERVER_ERROR)})
public @interface DefaultApiResponses {
}