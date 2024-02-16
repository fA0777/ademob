package com.falling.service;

import com.falling.pojo.Workers;


public interface WorkersService {
    /**
     * 根据用户名和密码查询
     * @param name
     * @param password
     * @return
     */
    Workers selectWorker(String name, String password);
}
