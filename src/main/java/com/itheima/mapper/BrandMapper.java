package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {
    // 查询所有
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    // 新增品牌数据
    @Insert("insert into tb_brand values(null , #{brandName}, #{companyName}, #{ordered}, #{description}, #{status})")
    @ResultMap("brandResultMap")
    void add(Brand brand);


    // 对数据进行更改
    @Update("update tb_brand set brand_name=#{brandName},company_name=#{companyName},ordered=#{ordered},description=#{description}, status=#{status} where id = #{id}")
    @ResultMap("brandResultMap")
    void update(Brand brand);

    // 批量删除
    void deleteByIds(@Param("ids") int[] ids);

    // 单个按钮删除
    @Delete("delete from tb_brand where id = #{id}")
    void deleteById(int id);

    // 页面查询
    // 1.返回查询的List集合,参数开始begin-步长size
    @Select("select * from tb_brand limit #{begin},#{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin")int begin, @Param("size")int size);

    // 2.返回查询的总条目数,返回数据库的总条目数
    @Select("select count(*) from tb_brand")
    int totalNum();

    // 条件分页查询
    // 1.获取查询后的list集合
    List<Brand> selectByPageAndCondition(@Param("begin")int begin, @Param("size")int size, @Param("brand") Brand brand);

    // 2.获取条件查询后的总数量
    int totalNumOnCondition(Brand brand);
}
