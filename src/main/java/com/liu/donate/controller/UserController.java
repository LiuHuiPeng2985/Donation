package com.liu.donate.controller;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/4 14:49
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IOUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.liu.donate.common.Result;
import com.liu.donate.entity.User;
import com.liu.donate.service.UserService;
import com.liu.donate.utils.FormatUtils;
import com.liu.donate.utils.Md5Utils;
import com.liu.donate.utils.StringConsts;
import com.liu.donate.utils.VoUtils;
import com.liu.donate.vo.request.*;
import com.liu.donate.vo.response.ResponseUserListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private Producer producer;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/register")
    public Result register(@RequestBody RequestRegisterVo requestRegisterVo){
        //校验参数是否为空
        if (VoUtils.checkObjFieldIsNull(requestRegisterVo)){
            return Result.getFailure().setMsg(StringConsts.MISS_REQUIRED_PARAMETERS);
        }
        //判断是否为电话号码
        if (!FormatUtils.isMobile(requestRegisterVo.getTelephone())){
            return Result.getFailure().setMsg(StringConsts.PHONE_ERROR);
        }
        //判断手机号是否已注册
        User user = userService.queryByTel(requestRegisterVo.getTelephone());
        if (ObjectUtils.isNotEmpty(user)){
            return Result.getFailure().setMsg(StringConsts.USER_IS_EXIST);
        }
        userService.register(requestRegisterVo);
        return Result.getSuccess().setMsg(StringConsts.REGISTER_SUCCESS);
    }

    @PostMapping("/login")
    public Result login(HttpServletRequest request,@RequestBody RequestLoginVo requestLoginVo){
        //验证码校验
//        HttpSession session =request.getSession();
//        String trueCaptcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String trueCaptcha = (String)redisTemplate.opsForValue().get(Constants.KAPTCHA_SESSION_KEY);
        //equalsIgnoreCase 不区分大小写
        if (!trueCaptcha.equalsIgnoreCase(requestLoginVo.getCaptcha())){
            return Result.getFailure().setMsg(StringConsts.CAPTCHA_ERROR);
        }
        //校验账号是否存在
        //判断手机号是否注册
        User user = userService.queryByTel(requestLoginVo.getTelephone());
        if (ObjectUtils.isEmpty(user)){
            return Result.getFailure().setMsg(StringConsts.USER_NOT_EXIST);
        }
        //校验密码是否正确
        if (!Md5Utils.hash(requestLoginVo.getPassword()).equals(user.getPassword())){
            return Result.getFailure().setMsg(StringConsts.PASSWORD_ERROR);
        }
        //判断角色，isAdmin==true:管理员，否则就是普通用户
        Map<String ,Object> data = new HashMap<>();
        data.put("userId",user.getId());
        data.put("isAdmin",user.getType());
        return Result.getSuccess().setData(data);
    }

    @GetMapping("/captcha.jpg")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control","no-store,no-cache");
        response.setContentType("image/jpeg");
        //生成文本类型的验证码
        String captcha = producer.createText();
        System.out.println("验证码:"+captcha);
//        HttpSession session = request.getSession();
//        session.setAttribute(Constants.KAPTCHA_SESSION_KEY,captcha);
//        session.setMaxInactiveInterval(60);
        ValueOperations forValue = redisTemplate.opsForValue();
        //保存验证码到Redis
        forValue.set(Constants.KAPTCHA_SESSION_KEY,captcha);
        redisTemplate.expire(Constants.KAPTCHA_SESSION_KEY,60, TimeUnit.SECONDS);
        BufferedImage bufferedImage = producer.createImage(captcha);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(bufferedImage,"jpg",outputStream);
        IOUtils.closeQuietly(outputStream);
    }

    @GetMapping("/getUserInfoById/{userId}")
    public Result getUserInfoById(@PathVariable Integer userId){
//        User user = userService.getById(userId);
//        ResponseUserListVo vo = new ResponseUserListVo();
//        BeanUtils.copyProperties(user,vo);
        ResponseUserListVo vo = userService.queryById(userId);
        return Result.getSuccess().setData(vo);
    }

    @PostMapping("/list")
    public Result list(@RequestBody RequestUserListVo requestUserListVo){
        IPage<ResponseUserListVo> voIPage = userService.userList(requestUserListVo);
        return Result.getSuccess().setData(voIPage);
    }

    @PostMapping("/changePassword")
    public Result changePassword(@RequestBody RequestChangePasswordVo changePasswordVo){
        User user = userService.getById(changePasswordVo.getId());
        if (user.getPassword().equals(Md5Utils.hash(changePasswordVo.getPassword()))){
            user.setPassword(Md5Utils.hash(changePasswordVo.getNewPassword()));
            userService.updateById(user);
            return Result.getSuccess().setMsg(StringConsts.UPDATE_SUCCESS);
        }
        else {
            return Result.getFailure().setMsg(StringConsts.UPDATE_FAIL);
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody RequestUpdateUserVo requestUpdateUserVo){
        User user = userService.getById(requestUpdateUserVo.getId());
        BeanUtils.copyProperties(requestUpdateUserVo,user);
        if (userService.updateById(user)){
            //删除缓存
            redisTemplate.delete("USER_"+requestUpdateUserVo.getId());
            return Result.getSuccess().setMsg(StringConsts.UPDATE_SUCCESS);
        }
        return Result.getFailure().setMsg(StringConsts.UPDATE_FAIL);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        if (userService.removeById(id)){
            //删除缓存
            redisTemplate.delete("USER_"+id);
            return Result.getSuccess().setMsg(StringConsts.DELETE_SUCCESS);
        }
        else {
            return Result.getFailure().setMsg(StringConsts.DELETE_FAIL);
        }
    }

}
