package com.rb.fmea.dao;

import com.rb.fmea.entities.Document;
import org.apache.ibatis.annotations.Mapper;

/**
 * @version v1.0
 * @ClassName: DocumentMapper
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/8 16:37
 */
@Mapper
public interface DocumentMapper {
    void insert(Document document);

    void update(Document document);

    void delete(int id);
}
