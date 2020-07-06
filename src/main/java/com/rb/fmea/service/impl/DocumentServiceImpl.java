package com.rb.fmea.service.impl;

import com.rb.fmea.dao.DocumentMapper;
import com.rb.fmea.entities.Document;
import com.rb.fmea.result.CodeMsg;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ReturnCode;
import com.rb.fmea.service.DocumentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @version v1.0
 * @ClassName: DocumentServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/8 16:42
 */
@Service
public class DocumentServiceImpl implements DocumentService {
    @Resource
    private DocumentMapper documentMapper;
    /**
     * @Author yyk
     * @Description //TODO 添加文件
     * @Date 2020/6/9 8:44
     * @Param [document]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result insert(Document document) {
        try {
            documentMapper.insert(document);
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }

    }

    /**
     * @Author yyk
     * @Description //TODO 修改文件
     * @Date 2020/6/9 8:27
     * @Param [document]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result update(Document document) {
        try {
            documentMapper.update(document);
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 删除文件
     * @Date 2020/6/9 8:27
     * @Param [id]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result delete(int id) {
        try {
            documentMapper.delete(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }
}
