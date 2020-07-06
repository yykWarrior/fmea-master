package com.rb.fmea.controller;

import com.rb.fmea.entities.Document;
import com.rb.fmea.result.Result;
import com.rb.fmea.service.DocumentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @version v1.0
 * @ClassName: DocumentController
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/8 16:40
 */
@Api(description = "文件化接口")
@RestController
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    /**
     * @Author yyk
     * @Description //TODO 插入文件化信息
     * @Date 2020/6/8 16:42
     * @Param [document]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "添加文件",notes = "添加文件")
    @RequestMapping(value = "document/insert",method = RequestMethod.GET)
    public Result insert(Document document){
        return documentService.insert(document);
    }


    /**
     * @Author yyk
     * @Description //TODO 修改文件化
     * @Date 2020/6/9 8:25
     * @Param [document]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "修改文件",notes = "修改文件")
    @RequestMapping(value = "document/update",method = RequestMethod.POST)
    public Result update(Document document){
        return documentService.update(document);
    }

    /**
     * @Author yyk
     * @Description //TODO 删除文件
     * @Date 2020/6/9 8:27
     * @Param [id]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "删除文件",notes = "删除文件")
    @RequestMapping(value = "document/delete",method = RequestMethod.GET)
    public Result update(int id){
        return documentService.delete(id);
    }

}
