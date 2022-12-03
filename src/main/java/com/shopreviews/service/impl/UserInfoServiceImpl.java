package com.shopreviews.service.impl;

import com.shopreviews.entity.UserInfo;
import com.shopreviews.mapper.UserInfoMapper;
import com.shopreviews.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyh
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
