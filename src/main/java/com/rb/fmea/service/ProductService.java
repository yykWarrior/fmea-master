package com.rb.fmea.service;

import com.rb.fmea.entities.Product;
import com.rb.fmea.page.PageParameter;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ResultDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @version v1.0
 * @ClassName: ProductService
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/20 14:47
 */
public interface ProductService {
    /**
     * @Author yyk
     * @Description //TODO 删除产品
     * @Date 2020/5/20 14:49
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    Result deleteProduct(String ids);

    /**
     * @Author yyk
     * @Description //TODO 更新产品信息
     * @Date 2020/5/20 15:47
     * @Param [product]
     * @return com.rb.fmea.result.Result
     **/
    Result updateProduct(Product product);

    /**
     * @Author yyk
     * @Description //TODO 查询产品
     * @Date 2020/5/20 16:03
     * @Param [name, pageParameter]
     * @return java.util.List<com.rb.fmea.entities.Product>
     **/
    ResultDto select(String name, PageParameter pageParameter);

    /**
     * @Author yyk
     * @Description //TODO
     * @Date 2020/5/20 17:09
     * @Param [multipartFile, product]
     * @return com.rb.fmea.result.Result
     **/
    Result insertProduct(MultipartFile multipartFile, Product product);
}
