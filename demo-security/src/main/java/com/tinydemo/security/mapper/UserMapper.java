package com.tinydemo.security.mapper;

import com.tinydemo.security.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

	@Select("select * from user")
	List<User> list();

	@Select("select * from user where username = #{username}")
	User getByUsername(String username);

	@Insert("insert into user(username, password, enabled) values(#{username}, #{password}, #{enabled})")
	void save(User user);

	@Update("update user set password = #{password}, enabled = #{enabled} where id = #{id}")
	void update(User user);

}
