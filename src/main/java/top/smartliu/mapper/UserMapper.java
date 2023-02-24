package top.smartliu.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.smartliu.pojo.User;

import java.util.List;

public interface UserMapper {
    User select(@Param("username") String username, @Param("password")  String password);
    List<User> selectAll();

    @Select("select * from tb_user where id  = #{id}")
    User selectById(int id);

    User selectByUsername(String username);

    @Insert("insert into tb_user values(null,#{username},#{password},null,null)")
    void add(User user);
}
