package com.cwh.springbootMybatis.util;

import com.github.pagehelper.PageHelper;

/**
 * @author weichunhe
 * created at 2018/11/30
 */
public class PageUtil {
    public static final int PAGE_SIZE = 10;

    /**
     * start paginate
     *
     * @param pageNum
     */
    public static void startPage(Integer pageNum) {
        if (pageNum == null) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, PAGE_SIZE);
    }
}
