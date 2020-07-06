package com.rb.fmea.service.impl;

import com.rb.fmea.dao.FmeaMapper;
import com.rb.fmea.dao.ProductMapper;
import com.rb.fmea.dao.ReviewMapper;
import com.rb.fmea.dao.ReviewResumeMapper;
import com.rb.fmea.entities.*;
import com.rb.fmea.page.PageParameter;
import com.rb.fmea.result.CodeMsg;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ResultDto;
import com.rb.fmea.result.ReturnCode;
import com.rb.fmea.service.ReviewService;
import com.rb.fmea.util.DateUtil;
import com.rb.fmea.util.StringProcess;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: ReviewServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/22 10:47
 */
@Service
public class ReviewServiceImpl implements ReviewService {
    @Resource
    private ReviewMapper reviewMapper;
    @Resource
    private ReviewResumeMapper reviewResumeMapper;
    @Resource
    private FmeaMapper fmeaMapper;
    @Resource
    private ProductMapper productMapper;



    /**
     * @Author yyk
     * @Description //TODO 新建评审
     * @Date 2020/6/22 10:48
     * @Param [review]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result insert(Review review) {
        try {
//            switch (type){
//                case 0:
//                    review.setStep("功能评审");
//                    //修改评审结果
//                    reviewResultService.updateResult(review.getFmeaId(),1,0);
//                    break;
//                case 1:
//                    review.setStep("失效评审");
//                    reviewResultService.updateResult(review.getFmeaId(),1,1);
//                    break;
//                case 2:
//                    review.setStep("风险评审");
//                    reviewResultService.updateResult(review.getFmeaId(),1,2);
//                    break;
//            }
            //review.setReviewDate(DateUtil.format(new Date()));
            review.setReviewState(1);
            review.setCreateDate(DateUtil.format(new Date()));
            reviewMapper.insert(review);
           /* //生成履历
            ReviewResume reviewResume = new ReviewResume();
            Fmea fmea = fmeaMapper.selectByPrimaryKey(review.getFmeaId());
            String fmeaName=fmea==null?"":fmea.getFmeaName();
            reviewResume.setFmeaName(fmeaName);
            reviewResume.setReviewContent(review.getReviewRequire());
            reviewResume.setReviewResult(review.getReviewResult());
            reviewResume.setReviewDate(review.getReviewDate());
            reviewResume.setOrganizer(review.getResponsible());
            reviewResumeMapper.insert(reviewResume);*/
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 删除评审
     * @Date 2020/6/22 11:03
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result delete(String ids) {
        try {
            ids = StringProcess.determinesWhetherStringEndsInComma(ids);
            String[] idArray = ids.split(",");
            for(String str:idArray) {
                reviewMapper.deleteByPrimaryKey(Integer.parseInt(str));
            }
            return Result.success();
        } catch (NumberFormatException e) {
            return Result.error(new CodeMsg(ReturnCode.DELETE_IS_ERROR,e.getMessage()));
        }

    }

    /**
     * @Author yyk
     * @Description //TODO 修改评审
     * @Date 2020/6/22 11:09
     * @Param [review]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result update(Review review) {
        try {
            reviewMapper.updateByPrimaryKey(review);
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 根据id修改评审结果
     * @Date 2020/6/23 9:15
     * @Param [id, reviewResult]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result updateById(int id, String reviewResult) {
        try{
        //根据id查询
        Review review=reviewMapper.selectByPrimaryKey(id);
        if(review==null){
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,"当前评审信息不存在"));
        }
        review.setReviewResult(reviewResult);
        review.setReviewDate(DateUtil.format(new Date()));
        review.setReviewState(2);

        //生成评审履历
        ReviewResume reviewResume = new ReviewResume();
        Fmea fmea = fmeaMapper.selectByPrimaryKey(review.getFmeaId());
        String fmeaName=fmea==null?"":fmea.getFmeaName();
        reviewResume.setFmeaName(fmeaName);
        reviewResume.setReviewContent(review.getReviewRequire());
        reviewResume.setReviewResult(review.getReviewResult());
        reviewResume.setReviewDate(review.getReviewDate());
        reviewResume.setOrganizer(review.getResponsible());
        reviewResumeMapper.insert(reviewResume);
        //更新评审信息
        update(review);
        return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }

    }

    /**
     * @Author yyk
     * @Description //TODO 分页查询评审结果
     * @Date 2020/6/27 15:44
     * @Param [pageParameter, productId]
     * @return com.rb.fmea.result.ResultDto
     **/
    @Override
    public ResultDto selectByPage(PageParameter pageParameter, int productId) {
        try {
            int page=(pageParameter.getPage()-1)*pageParameter.getLimit();
            List<Review> reviewList=reviewMapper.selectByPage(page,pageParameter.getLimit(),productId);
            //查询总条数
            int count=reviewMapper.count(productId);
            return new ResultDto(0,"",count,reviewList);
        } catch (Exception e) {
            return new ResultDto(1,e.getMessage());
        }
    }

    @Override
    public void create(int fmeaId, int structId, int state) {
        //插入评审信息
        //判断插入的信息是否已存在(根据fmeaId和结构查询评审信息是否已存在)
        Review review=reviewMapper.selectByFmeaIdAndStep(fmeaId,structId);
        if(review==null){
            Review review1 = new Review();
            //评审状态0表示待建计划，状态0表示总成
            review1.setFmeaId(fmeaId).setReviewState(0).setState(state).setStep(structId);
            reviewMapper.insert(review1);
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 定时修改评审状态
     * @Date 2020/7/4 16:48
     * @Param []
     * @return void
     **/
    @Override
    public void updateState() {
        //查询出所有的未建计划的评审
        List<Review> reviewList=reviewMapper.selectByReviewState(0);
        for(Review review:reviewList){
            //根据fmeaId查询出fmea的创建完成时间
            Fmea fmea = fmeaMapper.selectByPrimaryKey(review.getFmeaId());
            String finishDate = fmea.getFinishDate();
            int date = DateUtil.diffDate(new Date(), DateUtil.parseDate(finishDate));
            if(date>=90){
                review.setReviewState(3);
                reviewMapper.updateByPrimaryKey(review);
            }
        }

        //查出所有未实施评审
        List<Review> reviewList1 = reviewMapper.selectByReviewState(1);
        for(Review review:reviewList1){
            int date = DateUtil.diffDate(new Date(), DateUtil.parseDate(review.getCreateDate()));
            if(date>0){
                review.setReviewState(4);
                reviewMapper.updateByPrimaryKey(review);
            }
        }

        //完成9个月后转为待建
        List<Review> reviewList2 = reviewMapper.selectByReviewState(2);
        for(Review review:reviewList2){
            int date = DateUtil.diffDate(new Date(), DateUtil.parseDate(review.getCreateDate()));
            if(date>=270){
                review.setReviewState(0);
                reviewMapper.updateByPrimaryKey(review);
            }
        }

    }


}
