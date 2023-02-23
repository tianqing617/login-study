import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import top.smartliu.mapper.UserMapper;
import top.smartliu.pojo.User;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Login {
    public static void main(String[] args) throws IOException {
        // 1. 加载MyBatis核心配置文件，获取SqlSessionFactory实例
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2. 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 解决硬编码问题
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectAll();

        System.out.println(users.toString());

        //4. 释放资源
        sqlSession.close();
    }
}
