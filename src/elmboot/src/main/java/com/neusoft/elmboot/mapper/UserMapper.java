package com.neusoft.elmboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neusoft.elmboot.po.User;

@Mapper
public interface UserMapper {

	@Select("select * from user where userId=#{userId} and password=#{password}")
	public User getUserByIdByPass(User user);
	
	@Select("select count(*) from user where userId=#{userId}")
	public int getUserById(String userId);
	
	@Insert("insert into user values(#{userId},#{password},#{userName},#{userSex},null,1)")
	public int saveUser(User user);
	
	@Update("update user set userName=#{userName},userSex=#{userSex},userImg=#{userImg} where userId=#{userId} and password=#{password}")
	public int updateUser(User user);
	
	@Update("update user set delTag=0 where userId=#{userId} and password=#{password}")
	public int deleteUser(User user);
}
