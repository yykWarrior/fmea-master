package com.rb.fmea.service;

import com.rb.fmea.entities.Perm;

import java.util.List;

/**
 * @version v1.0
 * @ClassName: PermService
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/28 14:52
 */
public interface PermService {
    /**
     * @Author yyk
     * @Description //TODO 根据用户id查询对应的所有权限
     * @Date 2020/5/28 14:53
     * @Param [id]
     * @return java.util.List<com.rb.fmea.entities.Perm>
     **/
    List<Perm> selectPermByUserId(Integer id);
}
