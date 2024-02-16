package com.falling.mapper;

import com.falling.pojo.Workers;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface WorkersMapper {

    /**
     * 根据用户名和密码查询
     * @param name
     * @param password
     * @return
     */
    @Select("select * from ademob.workers where name=#{name} and password=#{password}")
    Workers selectWorker(@Param("name") String name, @Param("password") String password);

}
