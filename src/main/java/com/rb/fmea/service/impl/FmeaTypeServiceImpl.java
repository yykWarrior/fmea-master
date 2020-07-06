package com.rb.fmea.service.impl;

import com.rb.fmea.dao.FmeaTypeMapper;
import com.rb.fmea.dao.FmeaTypeProductMapper;
import com.rb.fmea.entities.FmeaType;
import com.rb.fmea.entities.FmeaTypeProduct;
import com.rb.fmea.page.PageParameter;
import com.rb.fmea.result.CodeMsg;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ResultDto;
import com.rb.fmea.result.ReturnCode;
import com.rb.fmea.service.FmeaTypeService;
import com.rb.fmea.util.StringProcess;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: FmeaTypeServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/19 10:09
 */
@Service
public class FmeaTypeServiceImpl implements FmeaTypeService {
    @Resource
    private FmeaTypeMapper fmeaTypeMapper;
    @Resource
    private FmeaTypeProductMapper fmeaTypeProductMapper;
    /**
     * @Author yyk
     * @Description //TODO 分页查询
     * @Date 2020/6/19 10:13
     * @Param [name, pageParameter]
     * @return com.rb.fmea.result.ResultDto
     **/
    @Override
    public ResultDto selectPage(String name, PageParameter pageParameter) {
        try {
            int page=(pageParameter.getPage()-1);
            name=StringProcess.stringConcate(name,"%");
            List<FmeaType> fmeaTypeList = fmeaTypeMapper.selectPage(name,page,pageParameter.getLimit());
            //总条数
            int count = fmeaTypeMapper.count(name);
            return new ResultDto(0,"",count,fmeaTypeList);
        } catch (Exception e) {
            return new ResultDto(1,e.getMessage());
        }

    }

    /**
     * @Author yyk
     * @Description //TODO 修改
     * @Date 2020/6/19 10:13
     * @Param [fmeaType]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result update(FmeaType fmeaType) {
        try {
            fmeaTypeMapper.updateByPrimaryKey(fmeaType);
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 查询所有
     * @Date 2020/6/19 10:33
     * @Param []
     * @return com.rb.fmea.result.ResultDto
     **/
    @Override
    public Result selectAll() {
        try {
            List<FmeaType> fmeaTypeList=fmeaTypeMapper.selectAll();
            return Result.success(fmeaTypeList);
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 删除
     * @Date 2020/6/19 10:37
     * @Param []
     * @return com.rb.fmea.result.Result
     *
     * @param ids*/
    @Override
    public Result delete(String ids) {
        try {
            ids = StringProcess.determinesWhetherStringEndsInComma(ids);
            String[] idArray = ids.split(",");
            for(int i=0;i<idArray.length;i++) {
                fmeaTypeMapper.deleteByPrimaryKey(Integer.parseInt(idArray[i]));
            }
            return Result.success();
        } catch (NumberFormatException e) {
            return Result.error(new CodeMsg(ReturnCode.DELETE_IS_ERROR,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 增加
     * @Date 2020/6/19 10:41
     * @Param [fmeaType]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result insert(FmeaType fmeaType) {
        try {
            fmeaTypeMapper.insert(fmeaType);
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }

    }

    /**
     * @Author yyk
     * @Description //TODO 在产品下添加类型/方向
     * @Date 2020/6/19 10:48
     * @Param [productId, typeId]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result insetByProduct(int productId, String typeId) {
        try {
            typeId = StringProcess.determinesWhetherStringEndsInComma(typeId);
            String[] array = typeId.split(",");
            for(String id:array) {
                FmeaTypeProduct fmeaTypeProduct = new FmeaTypeProduct();
                fmeaTypeProduct.setFmeaTypeId(Integer.parseInt(id));
                fmeaTypeProduct.setProductId(productId);
                fmeaTypeProductMapper.insert(fmeaTypeProduct);
            }
            return Result.success();
        } catch (NumberFormatException e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 在产品下查询
     * @Date 2020/6/19 10:55
     * @Param [productId]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result selectByProduct(int productId) {
        try {
            List<FmeaTypeProduct> fmeaTypeProductList=fmeaTypeProductMapper.selectByProductId(productId);
            return Result.success(fmeaTypeProductList);
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }

    }

    /**
     * @Author yyk
     * @Description //TODO 在产品下删除
     * @Date 2020/6/19 11:02
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result deleteByProduct(String ids) {
        try {
            ids = StringProcess.determinesWhetherStringEndsInComma(ids);
            String[] idArray = ids.split(",");
            for(int i=0;i<idArray.length;i++) {
                fmeaTypeProductMapper.deleteByPrimaryKey(Integer.parseInt(idArray[i]));
            }
            return Result.success();
        } catch (NumberFormatException e) {
            return Result.error(new CodeMsg(ReturnCode.DELETE_IS_ERROR,e.getMessage()));
        }
    }
}
