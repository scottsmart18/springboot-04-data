package com.davidniu.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    //查询数据库的所有信息，返回字符串
    //没有实体类，数据库中的东西，怎么获取？Map
    @GetMapping("/userList")
    public List<Map<String,Object>> userList(){
        String sql = "select * from user";
        List<Map<String,Object>> list_map = jdbcTemplate.queryForList(sql);
        return list_map;
    }
    @GetMapping("/addUser")
    public String addUser(){
        String sql = "insert into user (id,name,pwd) values (6,'小明','123456')";
        jdbcTemplate.update(sql);
        return "addUser ok";
    }
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id){
        String sql = "delete from user where id =?";
        jdbcTemplate.update(sql,id);
        return "deleteUser ok";
    }
    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id){
        String sql = "update user set name =?,pwd=? where id = "+id;
        Object[] objects = new Object[2];
        objects[0] = "小红";
        objects[1] = "654321";
        jdbcTemplate.update(sql,objects);
        return "updateUser ok";
    }
}
