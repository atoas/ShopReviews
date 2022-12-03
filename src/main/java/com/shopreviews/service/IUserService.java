package com.shopreviews.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shopreviews.dto.LoginFormDTO;
import com.shopreviews.dto.Result;
import com.shopreviews.entity.User;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyh
 */
public interface IUserService extends IService<User> {

    Result sendCode(String phone, HttpSession session);

    Result login(LoginFormDTO loginForm, HttpSession session);

    Result sign();

    Result signCount();

}
