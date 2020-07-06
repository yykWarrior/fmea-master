package com.rb.fmea.controller;

import com.rb.fmea.entities.Product;
import com.rb.fmea.page.PageParameter;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ResultDto;
import com.rb.fmea.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * @version v1.0
 * @ClassName: ProductController
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/20 14:27
 */
@Api(description = "产品信息接口")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * @Author yyk
     * @Description //TODO 添加产品
     * @Date 2020/5/20 14:44
     * @Param [multipartFile, product]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "添加产品",notes = "添加产品")
    @RequestMapping(value = "product/insert",method = RequestMethod.POST)
    public Result insertProduct(MultipartFile multipartFile, Product product){
        return productService.insertProduct(multipartFile,product);
    }


    /**
     * @Author yyk
     * @Description //TODO 删除产品
     * @Date 2020/5/20 14:50
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "删除产品")
    @RequestMapping(value = "product/delete/{ids}",method = RequestMethod.GET)
    public Result deleteProduct(@PathVariable(value = "ids") String ids){
        return productService.deleteProduct(ids);
    }

    /**
     * @Author yyk
     * @Description //TODO 修改产品信息有文件
     * @Date 2020/5/20 15:36
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "修改产品有文件",notes = "修改产品有文件")
    @RequestMapping(value = "product/update",method = RequestMethod.POST)
    public Result updateProduct(MultipartFile multipartFile,Product product){
        return productService.updateProduct(multipartFile,product);
    }


    /**
     * @Author yyk
     * @Description //TODO 修改产品信息无文件
     * @Date 2020/5/20 15:36
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "修改产品无文件",notes = "修改产品无文件")
    @RequestMapping(value = "product/updateNoFile",method = RequestMethod.POST)
    public Result updateProductNoFile(Product product){
        return productService.updateProductNoFile(product);
    }


    /**
     * @Author yyk
     * @Description //TODO 查询产品信息
     * @Date 2020/5/20 15:57
     * @Param [name]
     * @return com.rb.fmea.result.ResultDto
     **/
    @ApiOperation(value = "查询产品",notes = "查询产品")
    @RequestMapping(value = "product/select",method = RequestMethod.GET)
    public ResultDto select(String name, PageParameter pageParameter){
        return productService.select(name,pageParameter);
    }

}
