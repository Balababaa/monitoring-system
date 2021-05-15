package com.xbyrh.web.controller;

import com.xbyrh.common.annotations.NoAuth;
import com.xbyrh.common.exception.BadRequestException;
import com.xbyrh.common.utils.VerifyUtil;
import com.xbyrh.repo.model.mapper.AuthTokenMapper;
import com.xbyrh.repo.model.vo.MenuVO;
import com.xbyrh.service.IAuthService;
import com.xbyrh.repo.model.bo.AuthTokenBO;
import com.xbyrh.web.model.params.LoginParam;
import com.xbyrh.repo.model.vo.AuthTokenVO;
import com.xbyrh.repo.model.support.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * create at 2021/4/16
 *
 * @author chenxinhui
 */

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @Autowired
    private AuthTokenMapper authTokenMapper;

    @NoAuth
    @PostMapping("login")
    public AuthTokenVO auth(@RequestBody LoginParam loginParam, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String id = session.getId();

        String code = (String)session.getAttribute("SESSION_VERIFY_CODE_" + id);

        if (code == null || !code.equalsIgnoreCase(loginParam.getCode())) {
            session.removeAttribute("SESSION_VERIFY_CODE_" + id);
            throw new BadRequestException("验证码错误");
        }

        AuthTokenBO authTokenBO = authService.auth(loginParam.getUsername(), loginParam.getPassword());

        return authTokenMapper.toVO(authTokenBO);
    }

    @NoAuth
    @GetMapping("code")
    public BaseResponse<String> code(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        String id = session.getId();

        VerifyUtil.CodeImage codeImage = VerifyUtil.newBuilder()
                .setWidth(160)   //设置图片的宽度
                .setHeight(40)   //设置图片的高度
                .setSize(6)      //设置字符的个数
                .setLines(10)    //设置干扰线的条数
                .setFontSize(25) //设置字体的大小
                .setTilt(true)   //设置是否需要倾斜
                .setBackgroundColor(Color.WHITE) //设置验证码的背景颜色
                .build()         //构建VerifyUtil项目
                .createImage();//生成图片

        session.setAttribute("SESSION_VERIFY_CODE_" + id, codeImage.getCode());

        return BaseResponse.ok("data:image/png;base64," + codeImage.getBase64());
    }

    @GetMapping("menu")
    public List<MenuVO> menu(){
        return authService.menu();
    }

}
