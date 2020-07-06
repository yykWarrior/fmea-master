package com.rb.fmea.service.impl;

import com.rb.fmea.dao.FmeaMeasuresMapper;
import com.rb.fmea.dao.MeasuresMonitorMapper;
import com.rb.fmea.dao.ProductMapper;
import com.rb.fmea.dto.MeasureMonitorDto;
import com.rb.fmea.entities.FmeaMeasures;
import com.rb.fmea.entities.MeasuresMonitor;
import com.rb.fmea.entities.Product;
import com.rb.fmea.result.Result;
import com.rb.fmea.service.MeasuresMonitorService;
import com.rb.fmea.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: MeasuresMonitorServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/20 13:11
 */
@Service
public class MeasuresMonitorServiceImpl implements MeasuresMonitorService {
    @Resource
    private ProductMapper productMapper;
    @Resource
    private FmeaMeasuresMapper fmeaMeasuresMapper;
    @Resource
    private MeasuresMonitorMapper measuresMonitorMapper;
    /**
     * @Author yyk
     * @Description //TODO 定时生成措施监控值
     * @Date 2020/6/20 13:12
     * @Param []
     * @return void
     **/
    @Override
    public void insert() {
        int month = DateUtil.getMonth(DateUtil.addDate(new Date(), 1));
        //查询出所有的产品
        List<Product> productList = productMapper.selectAll();
        //为每个产品生成监控值
        for(Product product:productList){
            //查询这个产品下对应的措施
            //开放
            List<FmeaMeasures> fmeaMeasuresList0=fmeaMeasuresMapper.selectByProductIdAndState(product.getId(),0);
            //完成
            List<FmeaMeasures> fmeaMeasuresList1=fmeaMeasuresMapper.selectByProductIdAndState(product.getId(),1);
            //不执行
            List<FmeaMeasures> fmeaMeasuresList2=fmeaMeasuresMapper.selectByProductIdAndState(product.getId(),2);
           //保存开放的措施信息
            save(product.getId(),"开放",fmeaMeasuresList0.size(),month);
            //保存完成的措施信息
            save(product.getId(),"完成",fmeaMeasuresList1.size(),month);
            //保存不执行的措施信息
            save(product.getId(),"不执行",fmeaMeasuresList2.size(),month);
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 措施监控每种类型每个月数据
     * @Date 2020/6/20 14:29
     * @Param [productId]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result selectByTypeOrderMon(int productId) {
        //查询当前产品的措施监控的月份
        List<Integer> monList=measuresMonitorMapper.selectMonByProductId(productId);
        List<MeasureMonitorDto> measureMonitorDtoList=new ArrayList<>();
        for(Integer integer:monList){
            //根据月份和产品id查询每个类型每个月的总数
            Integer total0=measuresMonitorMapper.selectByLevelAndMonAndProductId("开放",integer,productId);
            int count0=total0==null?0:total0;
            Integer total1=measuresMonitorMapper.selectByLevelAndMonAndProductId("完成",integer,productId);
            int count1=total1==null?0:total1;
            Integer total2=measuresMonitorMapper.selectByLevelAndMonAndProductId("不执行",integer,productId);
            int count2=total2==null?0:total2;
            MeasureMonitorDto measureMonitorDto = create(count0, count1, count2, integer);
            measureMonitorDtoList.add(measureMonitorDto);
        }
        //查询当前月份
        //开放
        List<FmeaMeasures> fmeaMeasuresList0 = fmeaMeasuresMapper.selectByProductIdAndState(productId, 0);
        //完成
        List<FmeaMeasures> fmeaMeasuresList1 = fmeaMeasuresMapper.selectByProductIdAndState(productId, 1);
        //不执行
        List<FmeaMeasures> fmeaMeasuresList2 = fmeaMeasuresMapper.selectByProductIdAndState(productId, 2);
        MeasureMonitorDto measureMonitorDto = create(fmeaMeasuresList0.size(), fmeaMeasuresList1.size(), fmeaMeasuresList2.size(), DateUtil.getMonth(new Date()));
        measureMonitorDtoList.add(measureMonitorDto);
        return Result.success(measureMonitorDtoList);
    }

    /**
     * @Author yyk
     * @Description //TODO 保存措施监控数据
     * @Date 2020/6/20 13:48
     * @Param [productId, level, count]
     * @return void
     **/
    public void save(int productId,String level,int count,int month){
        MeasuresMonitor measuresMonitor = new MeasuresMonitor();
        measuresMonitor.setProductId(productId);
        measuresMonitor.setLevel(level);
        measuresMonitor.setMonth(month);
        measuresMonitor.setTotalNum(count);
        measuresMonitorMapper.insert(measuresMonitor);
    }


    public MeasureMonitorDto create(int count0,int count1,int count2,int month){
        MeasureMonitorDto measureMonitorDto = new MeasureMonitorDto();
        int total=count0+count1+count2;
        measureMonitorDto.setOpen(count0);
        measureMonitorDto.setComplete(count1);
        measureMonitorDto.setNoimplent(count2);
        measureMonitorDto.setMeasureTotal(total);
        double f1 = total==0?0.0:new BigDecimal((float)((double)count1/(double) total)*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        measureMonitorDto.setCompletion(f1);
        measureMonitorDto.setMon(month);
        return measureMonitorDto;
    }
}
