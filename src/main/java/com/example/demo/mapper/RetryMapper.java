package com.example.demo.mapper;

import com.example.demo.entity.Retry;
import com.example.demo.entity.RetryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RetryMapper {
    long countByExample(RetryExample example);

    int deleteByExample(RetryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Retry record);

    int insertSelective(Retry record);

    List<Retry> selectByExample(RetryExample example);

    Retry selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Retry record, @Param("example") RetryExample example);

    int updateByExample(@Param("record") Retry record, @Param("example") RetryExample example);

    int updateByPrimaryKeySelective(Retry record);

    int updateByPrimaryKey(Retry record);
}