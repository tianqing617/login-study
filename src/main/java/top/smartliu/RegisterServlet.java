package top.smartliu;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import top.smartliu.mapper.UserMapper;
import top.smartliu.pojo.User;
import top.smartliu.utils.SqlSessionFactoryUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet", value = "/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("register");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 0. 获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 1. 封装用户对象
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        // 2. 加载MyBatis核心配置文件，获取SqlSessionFactory实例
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        // 3. 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 4. 解决硬编码问题
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User existUser = userMapper.selectByUsername(username);

        // 5. 判断用户是否已注册
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();

        if (existUser == null) {
            // 增加用户
            userMapper.add(user);
            // 注意：必须提交事务
            sqlSession.commit();
            writer.write("add user");
        } else {
            writer.write("already user");
        }

        //6. 释放资源
        sqlSession.close();
    }
}
