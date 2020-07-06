package com.rb.fmea.service.impl;

import com.rb.fmea.dao.ApMonitorMapper;
import com.rb.fmea.dao.FmeaApMapper;
import com.rb.fmea.dao.FmeaFailAnalysisMapper;
import com.rb.fmea.dto.ApHvalueDto;
import com.rb.fmea.entities.ApMonitor;
import com.rb.fmea.entities.FmeaAp;
import com.rb.fmea.entities.FmeaFailAnalysis;
import com.rb.fmea.page.PageParameter;
import com.rb.fmea.result.CodeMsg;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ResultDto;
import com.rb.fmea.result.ReturnCode;
import com.rb.fmea.service.ApMonitorService;
import com.rb.fmea.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: ApMonitorServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/10 15:50
 */
@Service
public class ApMonitorServiceImpl implements ApMonitorService {
    @Resource
    private ApMonitorMapper apMonitorMapper;
    @Resource
    private FmeaFailAnalysisMapper fmeaFailAnalysisMapper;
    @Resource
    private FmeaApMapper fmeaApMapper;
    /**
     * @Author yyk
     * @Description //TODO 查询一个fmea下的ap监控值
     * @Date 2020/6/10 15:50
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result selectOneFmea(int fmeaId) {
        try {
            List<ApMonitor> apMonitorList = apMonitorMapper.selectOneFmea(fmeaId);
            //查询当前月份的ap值
            ApMonitor apMonitor = getApMonitor(fmeaId);
            apMonitorList.add(apMonitor);
            return Result.success(apMonitorList);
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 获取当前月份的ap监控值
     * @Date 2020/6/16 9:06
     * @Param [fmeaId]
     * @return com.rb.fmea.entities.ApMonitor
     **/
    public ApMonitor getApMonitor(int fmeaId){
        int hVal=0;
        int mVal=0;
        int lVal=0;

        //查询当前fmea下的所有的失效模式
        List<FmeaFailAnalysis> fmeaFailAnalysisList = fmeaFailAnalysisMapper.selectFailAnalysisByFmeaId(fmeaId);
        //查询每一个失效分析的ap值
        for(FmeaFailAnalysis fmeaFailAnalysis:fmeaFailAnalysisList){
            FmeaAp fmeaAp = fmeaApMapper.selectFailAnalysisId(fmeaFailAnalysis.getId());
            String apValue=fmeaAp==null?"":fmeaAp.getApValue();
            if("H".equals(apValue)){
                hVal++;
            }else if("M".equals(apValue)){
                mVal++;
            }else if("L".equals(apValue)){
                lVal++;
            }
        }

        ApMonitor apMonitor = new ApMonitor();
        apMonitor.setFmeaId(fmeaId);
        apMonitor.sethValue(hVal);
        apMonitor.setlValue(lVal);
        apMonitor.setmValue(mVal);
        apMonitor.setMonth(DateUtil.getMonth(DateUtil.addDate(new Date(), 1)));
        return apMonitor;
    }

    /**
     * @Author yyk
     * @Description //TODO 插入fmea信息
     * @Date 2020/6/16 9:55
     * @Param [apMonitor]
     * @return void
     **/
    @Override
    public void insert(ApMonitor apMonitor) {
        apMonitorMapper.insert(apMonitor);
    }


    /**
     * @Author yyk
     * @Description //TODO 查询一个fmea下高风险ap值
     * @Date 2020/6/16 11:12
     * @Param [fmeaId, pageParameter]
     * @return com.rb.fmea.result.ResultDto
     **/
    @Override
    public ResultDto selectHValue(int fmeaId, PageParameter pageParameter) {
        int page=(pageParameter.getPage()-1)*pageParameter.getLimit();
        //查询当前fmea下的所有的失效模式
        List<ApHvalueDto> apHvalueDtoList=fmeaApMapper.selectApValueByFmeaId(fmeaId,pageParameter.getLimit(),page);
        int count=fmeaApMapper.count(fmeaId);
        return new ResultDto(0,"",count,apHvalueDtoList);
    }
}
