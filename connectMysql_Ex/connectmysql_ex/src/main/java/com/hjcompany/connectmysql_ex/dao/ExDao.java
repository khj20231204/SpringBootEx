package com.hjcompany.connectmysql_ex.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.hjcompany.connectmysql_ex.model.ExBean;

@Mapper
public interface ExDao {

   public List<ExBean> getList();
}
