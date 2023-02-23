package top.smartliu.mapper;

import org.apache.ibatis.annotations.Select;
import top.smartliu.pojo.User;

import java.util.List;

public interface UserMapper {
    List<User> selectAll();

    @Select("select * from tb_user where id  = #{id}")
    User selectById(int id);
}
