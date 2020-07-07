package com.rb.fmea.service.impl;

import com.rb.fmea.dao.ProductMapper;
import com.rb.fmea.dao.ReviewResultSumMapper;
import com.rb.fmea.dto.ReviewResultDto;
import com.rb.fmea.entities.Product;
import com.rb.fmea.entities.ReviewResult;
import com.rb.fmea.entities.ReviewResultSum;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ResultDto;
import com.rb.fmea.service.ReviewResultSumService;
import com.rb.fmea.service.ReviewService;
import com.rb.fmea.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @version v1.0
 * @ClassName: ReviewResultSumServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/24 16:48
 */
@Service
public class ReviewResultSumServiceImpl implements ReviewResultSumService {
    @Resource
    private ProductMapper productMapper;
    @Autowired
    private ReviewService reviewService;
    @Resource
    private ReviewResultSumMapper reviewResultSumMapper;


    @Override
    public void insert() {
        int month = DateUtil.getMonth(new Date());
        //查询出所有的产品
        List<Product> productList = productMapper.selectAll();
        for(Product product:productList){
            ResultDto resultDto = reviewService.selectByPage(product.getId());
            List<ReviewResultDto> data = (List<ReviewResultDto>) resultDto.getData();
            List<Integer> integerList = selectSate(data);
            ReviewResultSum reviewResultSum0 = new ReviewResultSum("待建",month,product.getId(),integerList.get(0));
            reviewResultSumMapper.insert(reviewResultSum0);
            ReviewResultSum reviewResultSum1 = new ReviewResultSum("计划",month,product.getId(),integerList.get(1));
            reviewResultSumMapper.insert(reviewResultSum1);
            ReviewResultSum reviewResultSum2 = new ReviewResultSum("完成",month,product.getId(),integerList.get(2));
            reviewResultSumMapper.insert(reviewResultSum2);
            ReviewResultSum reviewResultSum3 = new ReviewResultSum("超期",month,product.getId(),integerList.get(3));
            reviewResultSumMapper.insert(reviewResultSum3);
            /*//查询出每个产品下的所有fmea的各种状态下的总数(0表示待建，1表示计划，2表示完成，3表示待建超期，4表示计划超期)
            List<ReviewResult> reviewResultList = re.selectByPage(1, 10000, product.getId());
            List<Integer> integerList = selectSate(reviewResultList);
            ReviewResultSum reviewResultSum0 = new ReviewResultSum("待建",month,product.getId(),integerList.get(0));
            reviewResultSumMapper.insert(reviewResultSum0);
            ReviewResultSum reviewResultSum1 = new ReviewResultSum("计划",month,product.getId(),integerList.get(1));
            reviewResultSumMapper.insert(reviewResultSum1);
            ReviewResultSum reviewResultSum2 = new ReviewResultSum("完成",month,product.getId(),integerList.get(2));
            reviewResultSumMapper.insert(reviewResultSum2);
            ReviewResultSum reviewResultSum3 = new ReviewResultSum("超期",month,product.getId(),integerList.get(3));
            reviewResultSumMapper.insert(reviewResultSum3);*/
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 根据产品id查询
     * @Date 2020/6/24 17:20
     * @Param [productId]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result selectByProductId(int productId) {
        List<ReviewResultSum> reviewResultSumList=reviewResultSumMapper.selectByProductId(productId);
        ResultDto resultDto = reviewService.selectByPage(productId);
        List<ReviewResultDto> data = (List<ReviewResultDto>) resultDto.getData();
        List<Integer> integerList = selectSate(data);
        int month = DateUtil.getMonth(new Date());
        ReviewResultSum reviewResultSum0 = new ReviewResultSum("待建",month,productId,integerList.get(0));
        ReviewResultSum reviewResultSum1 = new ReviewResultSum("计划",month,productId,integerList.get(1));
        ReviewResultSum reviewResultSum2 = new ReviewResultSum("完成",month,productId,integerList.get(2));
        ReviewResultSum reviewResultSum3 = new ReviewResultSum("超期",month,productId,integerList.get(3));
        reviewResultSumList.add(reviewResultSum0);
        reviewResultSumList.add(reviewResultSum1);
        reviewResultSumList.add(reviewResultSum2);
        reviewResultSumList.add(reviewResultSum3);
        Stream<ReviewResultSum> sorted = reviewResultSumList.stream().sorted((p1, p2) -> {
            if (p1.getMonth() == p1.getMonth()) {
                return p1.getReviewLevel().compareTo(p2.getReviewLevel());
            } else {
                return p1.getMonth().compareTo(p2.getMonth());
            }
        });
        List<ReviewResultSum> collect = sorted.collect(Collectors.toList());
        return Result.success(collect);
        //查询当前月份的
        //根据产品id查询出当前产品的所有评审信息
       /* List<ReviewResult> reviewResultList = .selectByPage(1, 10000, productId);
        List<Integer> integerList = selectSate(reviewResultList);
        int month = DateUtil.getMonth(new Date());
        ReviewResultSum reviewResultSum0 = new ReviewResultSum("待建",month,productId,integerList.get(0));
        ReviewResultSum reviewResultSum1 = new ReviewResultSum("计划",month,productId,integerList.get(1));
        ReviewResultSum reviewResultSum2 = new ReviewResultSum("完成",month,productId,integerList.get(2));
        ReviewResultSum reviewResultSum3 = new ReviewResultSum("超期",month,productId,integerList.get(3));
        reviewResultSumList.add(reviewResultSum0);
        reviewResultSumList.add(reviewResultSum1);
        reviewResultSumList.add(reviewResultSum2);
        reviewResultSumList.add(reviewResultSum3);
        Stream<ReviewResultSum> sorted = reviewResultSumList.stream().sorted((p1, p2) -> {
            if (p1.getMonth() == p1.getMonth()) {
                return p1.getReviewLevel().compareTo(p2.getReviewLevel());
            } else {
                return p1.getMonth().compareTo(p2.getMonth());
            }
        });
        List<ReviewResultSum> collect = sorted.collect(Collectors.toList());
        return Result.success(collect);*/
        //return Result.success(reviewResultSumList);
    }


    public List<Integer> selectSate(List<ReviewResultDto> reviewResultDtoList){
        int finshCount=0;
        int createCount=0;
        int planCount=0;
        int moreCount=0;
        List<Integer> list=new ArrayList<>();
        for(ReviewResultDto reviewResultDto:reviewResultDtoList){
            if(reviewResultDto.getReviewSate()==0){
                createCount++;
            }else if(reviewResultDto.getReviewSate()==1){
                planCount++;
            }else if(reviewResultDto.getReviewSate()==2){
                finshCount++;
            }else if(reviewResultDto.getReviewSate()==4){
                moreCount++;
            }
        }

        list.add(createCount);
        list.add(planCount);
        list.add(finshCount);
        list.add(moreCount);
        return list;

    }






    public List<Integer> selectSate1(List<ReviewResult> reviewResultList){
        int finshCount=0;
        int createCount=0;
        int planCount=0;
        int moreCount=0;
        List<Integer> list=new ArrayList<>();
        for(ReviewResult reviewResult:reviewResultList){
            Integer functionReviewResult = reviewResult.getFunctionReviewResult();
            switch (functionReviewResult){
                case 0:
                    createCount++;
                case 1:
                    planCount++;
                case 2:
                    finshCount++;
                case 3:
                    moreCount++;
            }

            Integer failAnalysisReviewResult = reviewResult.getFailAnalysisReviewResult();
            switch (failAnalysisReviewResult){
                case 0:
                    createCount++;
                case 1:
                    planCount++;
                case 2:
                    finshCount++;
                case 3:
                    moreCount++;
            }

            Integer riskReviewResult = reviewResult.getRiskReviewResult();
            switch (riskReviewResult){
                case 0:
                    createCount++;
                case 1:
                    planCount++;
                case 2:
                    finshCount++;
                case 3:
                    moreCount++;
            }
        }
        list.add(createCount);
        list.add(planCount);
        list.add(finshCount);
        list.add(moreCount);
        return list;

    }


}
