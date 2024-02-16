package com.falling.service.impl;

import com.falling.mapper.WorkersMapper;
import com.falling.service.WorkersService;
import com.falling.util.SqlSessionFactoryUtils;
import com.falling.pojo.Workers;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class WorkersServiceImpl implements WorkersService {
    //创建SqlSessionFactory工厂对象
    SqlSessionFactory factory=SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public Workers selectWorker(String name, String password) {
        //获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //获取WorkersMapper
        WorkersMapper mapper = sqlSession.getMapper(WorkersMapper.class);
        //调用方法
        Workers worker = mapper.selectWorker(name, password);
        //释放资源
        sqlSession.close();
        return worker;
    }
}
