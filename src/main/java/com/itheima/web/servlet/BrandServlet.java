package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.Pagebean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private BrandService brandService = new BrandServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取方法
        List<Brand> brands = brandService.selectAll();

        // 2.转化为json数据,还要设置中文格式
        response.setContentType("text/json;charset=utf-8");
        String jsonString = JSON.toJSONString(brands);

        // 3.发送出去
        response.getWriter().write(jsonString);

    }
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取从前端提交的json数据
        BufferedReader br = request.getReader();
        String params = br.readLine();  // json字符串
        // 2.转化为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        // 3.添加到数据库中
        brandService.add(brand);

        // 4.返回一个添加成功的结果
        response.getWriter().write("success");
    }

    // 修改数据
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取从前端提交的json数据
        BufferedReader br = request.getReader();
        String params = br.readLine();  // json字符串
//        System.out.println(params);  // 从前端读取到的数据长什么样子

        // 2.转化为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
//        System.out.println(brand);  // 解析成Brand类后长什么样子

        // 3.调用方法，修改到数据库
        brandService.update(brand);

        // 3.返回一个删除成功的结果
        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取从前端提交的json数据
        BufferedReader br = request.getReader();
        String params = br.readLine();  // json字符串
        System.out.println("前端提交的json字符串:" + params);

        // 2.转化为数组
        int[] ints = JSON.parseObject(params, int[].class);
        System.out.println("转化为数组之后:" + ints);

        // 3.添加到数据库中
        brandService.deleteByIds(ints);

        // 4.返回一个删除成功的结果
        response.getWriter().write("success");
    }

    // 单个选项的id删除
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取从前端提交的id数据
        String id = request.getParameter("id");

        // 2.添加到数据库中
        brandService.deleteById(Integer.parseInt(id));

        // 3.返回一个删除成功的结果
        response.getWriter().write("success");
    }

    // 分页查询，被后面的分页条件查询覆盖掉了，即使删了也没有影响。
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取从前端提交的json数据
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");

        // 2.从数据库中查询
        Pagebean<Brand> pageBean = brandService.pageSelect(Integer.parseInt(currentPage), Integer.parseInt(pageSize));

        // 3.返回json数据，将实体类转换成json格式
        String s = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }

    // 分页条件查询
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取从前端提交的json数据
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");

        // 获取条件查询对象且转为Brand
        BufferedReader br = request.getReader();
        String params = br.readLine();  // json字符串
        Brand brand = JSON.parseObject(params, Brand.class);

        // 2.从数据库中查询
        Pagebean<Brand> pageBean = brandService.selectByPageAndCondition(Integer.parseInt(currentPage), Integer.parseInt(pageSize), brand);

        // 3.返回json数据，将实体类转换成json格式
        String s = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }



}
