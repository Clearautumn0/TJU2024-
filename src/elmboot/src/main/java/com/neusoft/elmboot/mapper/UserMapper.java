package com.neusoft.elmboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neusoft.elmboot.po.User;

@Mapper
public interface UserMapper {

	@Select("select * from user where userId=#{userId} and password=#{password} and delTag=0")
	public User getUserByIdByPass(User user);
	
	@Select("select count(*) from user where userId=#{userId} and delTag=0")
	public int getUserById(String userId);
	
	@Insert("insert into user values(#{userId},#{password},#{userName},#{userSex},null,0)")
	public int saveUser(User user);
	
	@Update("update user set userName=#{userName},userSex=#{userSex},userImg=#{userImg} where userId=#{userId} and password=#{password}")
	public int updateUser(User user);
	
	@Update("update user set delTag=1 where userId=#{userId} and password=#{password}")
	public int deleteUser(User user);
}