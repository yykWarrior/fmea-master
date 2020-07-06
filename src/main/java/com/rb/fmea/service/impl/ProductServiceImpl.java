package com.rb.fmea.service.impl;

import com.rb.fmea.dao.ProductMapper;
import com.rb.fmea.entities.Product;
import com.rb.fmea.file.UploadFile;
import com.rb.fmea.page.PageParameter;
import com.rb.fmea.result.CodeMsg;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ResultDto;
import com.rb.fmea.result.ReturnCode;
import com.rb.fmea.service.ProductService;
import com.rb.fmea.util.StringProcess;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: ProductServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/20 14:47
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;
    private final static String PATH="product";
    /**
     * @Author yyk
     * @Description //TODO 删除产品
     * @Date 2020/5/20 14:49
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result deleteProduct(String ids) {
        try {
            String[] idArray = StringProcess.spliteString(ids, ",");
            for(int i=0;i<idArray.length;i++){
                productMapper.deleteByPrimaryKey(Integer.parseInt(idArray[i]));
            }
        } catch (NumberFormatException e) {
            return Result.error(new CodeMsg(ReturnCode.DELETE_IS_ERROR,e.getMessage()));
        }
        return Result.success();
    }

    /**
     * @Author yyk
     * @Description //TODO 更新产品信息
     * @Date 2020/5/20 15:47
     * @Param [product]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result updateProduct(MultipartFile multipartFile, Product product) {
        try {
            String newName = UploadFile.oneUploadFile(multipartFile, PATH);
            product.setProductPath(PATH+"/"+newName);
            productMapper.updateByPrimaryKey(product);
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }

    }

    /**
     * @Author yyk
     * @Description //TODO 查询产品
     * @Date 2020/5/20 16:03
     * @Param [name, pageParameter]
     * @return java.util.List<com.rb.fmea.entities.Product>
     **/
    @Override
    public ResultDto select(String name, PageParameter pageParameter) {

        List<Product> productList= null;
        try {
            int page=(pageParameter.getPage()-1)*pageParameter.getLimit();
            name = StringProcess.stringConcate(name, "%");
            productList = productMapper.selectByNameAndPage(name,page,pageParameter.getLimit());
            int count=productMapper.count(name);
            return new ResultDto(0,"",count,productList);
        } catch (Exception e) {
            return new ResultDto(1,"",0,"查询失败,原因:"+e.getMessage());
        }

    }

    /**
     * @Author yyk
     * @Description //TODO 添加产品
     * @Date 2020/5/20 16:58
     * @Param [multipartFile, product]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result insertProduct(MultipartFile multipartFile, Product product) {
        try {
            //上传文件
            String newName = UploadFile.oneUploadFile(multipartFile, PATH);
            product.setProductPath(PATH+"/"+newName);
            productMapper.insert(product);
            return Result.success();
        } catch (IOException e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 修改产品信息无文件
     * @Date 2020/6/17 14:53
     * @Param [product]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result updateProductNoFile(Product product) {
        try {
            productMapper.updateByPrimaryKey(product);
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }
}
