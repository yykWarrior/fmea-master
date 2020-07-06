package com.rb.fmea.service.impl;

import com.rb.fmea.dao.FmeaMapper;
import com.rb.fmea.service.FmeaDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @version v1.0
 * @ClassName: FmeaDeleteServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/7/3 15:58
 */
@Service
public class FmeaDeleteServiceImpl implements FmeaDeleteService {


    @Resource
    private FmeaMapper fmeaMapper;
    /**
     * @Author yyk
     * @Description //TODO 根据不同部分删除对应下的所有关联的信息
     * @Date 2020/7/3 15:59
     * @Param [type, id]
     * @return java.lang.Boolean
     **/
    public Boolean deleteParts(int type,int id){
        switch (type){
            case 0:
                fmeaMapper.deleteByProductId(id);
            case 1:
            case 2:
            case 3:
        }
        return true;
    }


}
