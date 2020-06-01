package com.rb.fmea.service.impl;

import com.rb.fmea.dao.PermMapper;
import com.rb.fmea.entities.Perm;
import com.rb.fmea.service.PermService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: PermServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/28 14:52
 */
@Service
public class PermServiceImpl implements PermService {
    @Resource
    private PermMapper permMapper;
    /**
     * @Author yyk
     * @Description //TODO 根据用户id查询对应的权限
     * @Date 2020/5/28 14:53
     * @Param [id]
     * @return java.util.List<com.rb.fmea.entities.Perm>
     **/
    @Override
    public List<Perm> selectPermByUserId(Integer id) {
        return permMapper.selectPermByUserId(id);
    }
}
