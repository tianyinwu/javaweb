package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.Pagebean;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    // 1.创建sqlSessionFactory对象
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    // 获取所有数据信息的
    @Override
    public List<Brand> selectAll() {
        // 2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.获取mapper对象，映射关系出来了
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4.调用mapper的方法
        List<Brand> brands = mapper.selectAll();

        // 5.关闭sqlSession
        sqlSession.close();

        return brands;
    }

    // 增加品牌数据的
    @Override
    public void add(Brand brand) {
        // 2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.获取mapper对象，映射关系出来了
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4.调用mapper的方法
        mapper.add(brand);

        // 5.增加需要提交事务
        sqlSession.commit();

        // 6.关闭资源
        sqlSession.close();
    }


    // 修改
    @Override
    public void update(Brand brand) {
        // 2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.获取mapper对象，映射关系出来了
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4.调用mapper的方法
        mapper.update(brand);

        // 5.提交事务
        sqlSession.commit();

        // 5.关闭资源
        sqlSession.close();
    }

    // 批量删除
    @Override
    public void deleteByIds(int[] ids) {
        // 2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.获取mapper对象，映射关系出来了
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4.调用mapper的方法
        mapper.deleteByIds(ids);

        // 5.提交事务
        sqlSession.commit();

        // 6.关闭资源
        sqlSession.close();
    }

    @Override
    public void deleteById(int id) {
        // 2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.获取mapper对象，映射关系出来了
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4.调用mapper的方法
        mapper.deleteById(id);

        // 5.提交事务
        sqlSession.commit();

        // 6.关闭资源
        sqlSession.close();
    }

    // 页面查询
    @Override
    public Pagebean<Brand> pageSelect(int currentPage, int pageSize) {
        // 2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.获取mapper对象，映射关系出来了
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4.调用mapper的方法
        List<Brand> rows = mapper.selectByPage((currentPage-1)*pageSize, pageSize);  // 开始目录=(当前目录-1)*pageSize  步长 = pageSize
        int num = mapper.totalNum();

        // 5.PageBean的封装
        Pagebean<Brand> pageBean = new Pagebean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(num);

        // 资源释放
        sqlSession.close();

        // 返回参数
        return pageBean;
    }

    @Override
    public Pagebean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        // 2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.获取mapper对象，映射关系出来了
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 给brand参数加上模糊查询,只用对两个参数加就行了
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0){
            brand.setBrandName("%"+brandName+"%");
        }
        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length()>0){
            brand.setCompanyName("%"+companyName+"%");
        }
        // 4.调用mapper的方法
        List<Brand> rows = mapper.selectByPageAndCondition((currentPage-1)*pageSize, pageSize, brand);  // 开始目录=(当前目录-1)*pageSize  步长 = pageSize
        int num = mapper.totalNumOnCondition(brand);

        // 5.PageBean的封装
        Pagebean<Brand> pageBean = new Pagebean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(num);

        // 资源释放
        sqlSession.close();

        // 返回参数
        return pageBean;
    }


}
