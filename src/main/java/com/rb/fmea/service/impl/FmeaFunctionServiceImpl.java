package com.rb.fmea.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rb.fmea.dao.FmeaFunctionMapper;
import com.rb.fmea.dao.FmeaFunctionRelateMapper;
import com.rb.fmea.dao.FmeaStructureMapper;
import com.rb.fmea.dto.FmeaFunctionDto;
import com.rb.fmea.dto.FmeaStructureDto;
import com.rb.fmea.dto.FunctionRelateDto;
import com.rb.fmea.dto.StructureFunctionDto;
import com.rb.fmea.entities.FmeaFunction;
import com.rb.fmea.entities.FmeaFunctionRelate;
import com.rb.fmea.entities.FmeaStructure;
import com.rb.fmea.result.CodeMsg;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ReturnCode;
import com.rb.fmea.service.FmeaFunctionService;
import com.rb.fmea.util.StringProcess;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @version v1.0
 * @ClassName: FmeaFunctionServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/23 16:57
 */
@Service
public class FmeaFunctionServiceImpl implements FmeaFunctionService {
    @Resource
    private FmeaFunctionMapper fmeaFunctionMapper;
    @Resource
    private FmeaStructureMapper fmeaStructureMapper;
    @Resource
    private FmeaFunctionRelateMapper fmeaFunctionRelateMapper;
    /**
     * @Author yyk
     * @Description //TODO 批量增加功能
     * @Date 2020/5/23 16:59
     * @Param [functions]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result insert(String functions, int structureId) {
        try {
            JSONArray jsonArray = JSONArray.parseArray(functions);
            List<FmeaFunction> list = JSONObject.parseArray(jsonArray.toJSONString(), FmeaFunction.class);
            for(FmeaFunction fmeaFunction:list){
                fmeaFunction.setFmeaStructureId(structureId);
                fmeaFunctionMapper.insert(fmeaFunction);
            }
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 批量删除fmea功能
     * @Date 2020/5/25 9:43
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result delete(String ids) {
        try {
            String[] idArray = StringProcess.spliteString(ids, ",");
            for(int i=0;i<idArray.length;i++){
                fmeaFunctionMapper.deleteByPrimaryKey(Integer.parseInt(idArray[i]));
            }
            return Result.success();
        } catch (NumberFormatException e) {
            return Result.error(new CodeMsg(ReturnCode.DELETE_IS_ERROR,e.getMessage()));
        }
    }


    /**
     * @Author yyk
     * @Description //TODO 修改fmea功能
     * @Date 2020/5/25 9:51
     * @Param [fmeaFunction]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result update(FmeaFunction fmeaFunction) {
        try {
            fmeaFunctionMapper.updateByPrimaryKey(fmeaFunction);
            //生成履历信息
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 查询一个结构下的所有功能
     * @Date 2020/5/25 13:18
     * @Param [structureId]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result selectOneStructure(int structureId) {
        try {
            List<FmeaFunctionDto> fmeaStructureList=fmeaFunctionMapper.selectOneStructure(structureId);
            return Result.success(fmeaStructureList);
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 查询单个fmea下所有功能
     * @Date 2020/5/25 13:47
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result selectOneFmea(int fmeaId) {
        try {
            List<FmeaFunctionDto> fmeaFunctionList=fmeaFunctionMapper.selectOneFmea(fmeaId);
            return Result.success(fmeaFunctionList);
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 一个fmea下各个结构的功能之间建立关系
     * @Date 2020/5/25 14:18
     * @Param [structureId0, structureId1]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result setFunctionRelate(int functionId0, String functionIds) {
        try {
        String[] functionArray = StringProcess.spliteString(functionIds, ",");
        for(String functionId1:functionArray) {
            //查询两个功能的基本信息
            FmeaFunction fmeaFunction = fmeaFunctionMapper.selectByPrimaryKey(functionId0);
            FmeaFunction fmeaFunction1 = fmeaFunctionMapper.selectByPrimaryKey(Integer.parseInt(functionId1));
            //两个功能不在同一结构下，才去建立关系
            if (fmeaFunction.getFmeaStructureId() != fmeaFunction1.getFmeaStructureId()) {
                //查询是否在同一个fmea下面
                FmeaStructure fmeaStructure = fmeaStructureMapper.selectByPrimaryKey(fmeaFunction.getFmeaStructureId());
                FmeaStructure fmeaStructure1 = fmeaStructureMapper.selectByPrimaryKey(fmeaFunction1.getFmeaStructureId());
                //判断是否在同一个fmea下,不是同一个fmea建立不了关系
                if (fmeaStructure.getFmeaId() == fmeaStructure1.getFmeaId()) {
                    FmeaFunctionRelate fmeaFunctionRelate = new FmeaFunctionRelate();
                    fmeaFunctionRelate.setFmea_id(fmeaStructure.getFmeaId());
                    fmeaFunctionRelate.setFunctionNextId(Integer.parseInt(functionId1));
                    fmeaFunctionRelate.setFunctionSuperiorId(functionId0);
                    fmeaFunctionRelateMapper.insert(fmeaFunctionRelate);
                }
            }
        }
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG, e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 查询一个结构下的上级结构和自己以及下级结构功能
     * @Date 2020/5/25 16:07
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    /**
     * {
     *   "message": "成功",
     *   "data": [
     *     {
     *       "structureId": 11,
     *       "parentFmeaFunctionList": [
     *         {
     *           "id": 10,
     *           "structureName": "总成1",
     *           "structureDesc": "总成1描述",
     *           "fmeaId": null,
     *           "fmeaStructureDtoList": null,
     *           "fmeaFunctionDtoList": [
     *             {
     *               "id": 2,
     *               "integrate": "3",
     *               "functionRequire": "要求2",
     *               "structureId": 10,
     *               "structureName": "总成1",
     *               "structureDesc": "总成1描述"
     *             }
     *           ],
     *           "fmeaFunctionList": null
     *         }
     *       ],
     *       "functionId": 3,
     *       "functionDesc": "4",
     *       "functionRequire": "要求3",
     *       "nextFmeaFunctionList": [
     *         {
     *           "id": 15,
     *           "structureName": "特性3",
     *           "structureDesc": "特性3描述",
     *           "fmeaId": null,
     *           "fmeaStructureDtoList": null,
     *           "fmeaFunctionDtoList": [
     *             {
     *               "id": 4,
     *               "integrate": "2",
     *               "functionRequire": "要求4",
     *               "structureId": 15,
     *               "structureName": "特性3",
     *               "structureDesc": "特性3描述"
     *             }
     *           ],
     *           "fmeaFunctionList": null
     *         }
     *       ]
     *     },
     *     {
     *       "structureId": 11,
     *       "parentFmeaFunctionList": [],
     *       "functionId": 8,
     *       "functionDesc": "4",
     *       "functionRequire": "要求9",
     *       "nextFmeaFunctionList": []
     *     }
     *   ],
     *   "retCode": 200
     * }
     **/
    @Override
    public Result selectFunctionRelate(int structureId) {
        List<FunctionRelateDto> functionRelateDtoList=new ArrayList<>();
        //查询当前结构下的所有功能
        List<FmeaFunctionDto> fmeaFunctionDtoList = fmeaFunctionMapper.selectOneStructure(structureId);
        for(FmeaFunctionDto fmeaFunctionDto:fmeaFunctionDtoList){
            FunctionRelateDto functionRelateDto = new FunctionRelateDto();
            functionRelateDto.setFunctionDesc(fmeaFunctionDto.getFunctionDesc());
            functionRelateDto.setFunctionRequire(fmeaFunctionDto.getFunctionRequire());
            functionRelateDto.setFunctionId(fmeaFunctionDto.getId());
            functionRelateDto.setStructureId(structureId);
            //查询下一级结构以及功能名称（根据功能关系表查询当前功能对应的所有功能，然后查询出每个功能对应的结构）
            List<StructureFunctionDto> structureFunctionDto=fmeaFunctionRelateMapper.selectNextByStructureId(fmeaFunctionDto.getId());
            List<FmeaStructureDto> fmeaStructureDtoList1 = getGroupBy(structureFunctionDto);
            functionRelateDto.setNextFmeaFunctionList(fmeaStructureDtoList1);

            //查询下一级结构以及功能名称
            List<StructureFunctionDto> structureFunctionDto1=fmeaFunctionRelateMapper.selectParentByStructureId(fmeaFunctionDto.getId());
            List<FmeaStructureDto> fmeaStructureDtoList2 = getGroupBy(structureFunctionDto1);
            functionRelateDto.setParentFmeaFunctionList(fmeaStructureDtoList2);
            functionRelateDtoList.add(functionRelateDto);
        }
        return Result.success(functionRelateDtoList);
    }

    /**
     * @Author yyk
     * @Description //TODO 查询当前结构的下级结构的所有功能
     * @Date 2020/5/30 11:17
     * @Param [structureId]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result selectNextStructureAndFunction(int structureId) {
        try {
            FmeaStructure fmeaStructure = fmeaStructureMapper.selectByPrimaryKey(structureId);
            //查询当前结构对应的下级结构
            List<FmeaStructureDto> fmeaStructureDtoList = fmeaStructureMapper.selectBySuperiorIdAndFmeaId(structureId, fmeaStructure == null ? 0 : fmeaStructure.getFmeaId());
            for(FmeaStructureDto fmeaStructureDto:fmeaStructureDtoList){
                List<FmeaFunctionDto> fmeaFunctionDtoList = fmeaFunctionMapper.selectOneStructure(fmeaStructureDto.getId());
                fmeaStructureDto.setFmeaFunctionList(fmeaFunctionDtoList);
            }
            return Result.success(fmeaStructureDtoList);
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }


    /**
     * @Author yyk
     * @Description //TODO 把查询的结果以结构分组，封装成每个结构下放对应多个功能模式
     * @Date 2020/5/27 16:37
     * @Param [structureFunctionDtoList]
     * @return java.util.List<com.rb.fmea.dto.FmeaStructureDto>
     **/
    public  List<FmeaStructureDto> getGroupBy(List<StructureFunctionDto> structureFunctionDtoList){
        //以结构id分组查询的结果
        Map<Integer, List<StructureFunctionDto>> structureMap = structureFunctionDtoList.stream().collect(Collectors.groupingBy(StructureFunctionDto::getStructureId));
        Iterator<Map.Entry<Integer, List<StructureFunctionDto>>> iterator = structureMap.entrySet().iterator();
        List< FmeaStructureDto> fmeaStructureDtoList=new ArrayList<>();
        while(iterator.hasNext()){
            Map.Entry<Integer, List<StructureFunctionDto>> next = iterator.next();
            Integer key = next.getKey();
            List<StructureFunctionDto> value = next.getValue();
            if(key!=null&&key!=0&&value.size()!=0){
                StructureFunctionDto structureFunctionDto = value.get(0);
                FmeaStructureDto fmeaStructureDto = new FmeaStructureDto();
                fmeaStructureDto.setId(key);
                fmeaStructureDto.setStructureDesc(structureFunctionDto.getStructureDesc());
                fmeaStructureDto.setStructureName(structureFunctionDto.getStructureName());
                fmeaStructureDto.setFmeaFunctionDtoList(value);
                fmeaStructureDtoList.add(fmeaStructureDto);
            }
        }
        return fmeaStructureDtoList;
    }
}
