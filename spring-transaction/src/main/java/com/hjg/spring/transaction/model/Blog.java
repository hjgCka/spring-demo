package com.hjg.spring.transaction.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/2/4
 */
@Data
@Accessors(chain = true)
public class Blog {
    String id;
    String title;
    Date createTime;
}
