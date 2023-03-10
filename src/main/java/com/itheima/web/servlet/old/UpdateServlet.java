package com.itheima.web.servlet.old;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {
    private BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.处理收到信息中文乱码的问题
        request.setCharacterEncoding("utf-8");

        // 2.返回得到的信息,json格式
        BufferedReader reader = request.getReader();
        String s = reader.readLine();

        // 3.将json格式的转为brand类
        Brand brand = JSON.parseObject(s, Brand.class);

        // 4.调用方法执行
        brandService.update(brand);

        // 4.发送更新成功命令
        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
