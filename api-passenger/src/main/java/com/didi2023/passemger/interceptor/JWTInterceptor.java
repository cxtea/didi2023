package com.didi2023.passemger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.didi2023.internalcommon.constant.dto.ResponseResult;
import com.didi2023.internalcommon.constant.util.JWTUtils;
import net.sf.json.JSONObject;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.SignatureException;

public class JWTInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean result = true;
        String resultString = "";

        String token = request.getHeader("Authorization");

        try {
            JWTUtils.parseToken(token);
        } catch (SignatureVerificationException e) {
            resultString = "token sign error";
            result = false;
        } catch (TokenExpiredException e) {
            resultString = "token expired";
            result = false;
        } catch (AlgorithmMismatchException e) {
            resultString = "token algorithm error";
            result = false;
        } catch (Exception e) {
            resultString = "token error";
            result = false;
        }

        if (!result) {
            PrintWriter writer = response.getWriter();
            writer.print(JSONObject.fromObject(ResponseResult.fail(resultString)));
        }

        return result;
    }
}
