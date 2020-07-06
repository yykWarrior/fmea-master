package com.rb.fmea.service;

import com.rb.fmea.entities.Document;
import com.rb.fmea.result.Result;

/**
 * @version v1.0
 * @ClassName: DocumentService
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/8 16:42
 */
public interface DocumentService {
    Result insert(Document document);

    Result update(Document document);

    Result delete(int id);
}
