package com.shopreviews.service.impl;

import com.shopreviews.entity.BlogComments;
import com.shopreviews.mapper.BlogCommentsMapper;
import com.shopreviews.service.IBlogCommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 * @author zyh
 */
@Service
public class BlogCommentsServiceImpl extends ServiceImpl<BlogCommentsMapper, BlogComments> implements IBlogCommentsService {

}
