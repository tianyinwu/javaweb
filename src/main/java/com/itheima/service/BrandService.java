package com.itheima.service;

import com.itheima.pojo.Brand;
import com.itheima.pojo.Pagebean;

import java.util.List;

public interface BrandService {
    // 查询所有
    List<Brand> selectAll();

    // 增加品牌数据
    void add(Brand brand);

    // 修改品牌数据
    void update(Brand brand);  // 修改

    // 批量删除
    void deleteByIds(int[] ids);

    // 单个删除
    void deleteById(int id);

    // 分页查询
    Pagebean<Brand> pageSelect(int currentPage, int pageSize);

    // 分页条件查询
    Pagebean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand);


}
