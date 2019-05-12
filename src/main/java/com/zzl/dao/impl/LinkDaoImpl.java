package com.zzl.dao.impl;

import com.zzl.dao.ILinkDao;
import com.zzl.model.bo.LinkUrlBo;
import com.zzl.util.JdbcUtil;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("LinkDao")
public class LinkDaoImpl implements ILinkDao {


    @Override
    public Integer updateLinkUrl(LinkUrlBo linkUrlBo) {
        JdbcUtil jdbcUtil=new JdbcUtil();
        String sql="update LinkUrl set count=? where code=?";
        int result=jdbcUtil.update(sql, linkUrlBo.getCount(),linkUrlBo.getBaseUrl());
        return result;
    }

    @Override
    public Integer insertLinkUrl(LinkUrlBo linkUrlBo) {
        JdbcUtil jdbcUtil=new JdbcUtil();

        String sql="insert into LinkUrl(code,baseUrl) values(?,?)";
        int result=jdbcUtil.update(sql, linkUrlBo.getCode(),linkUrlBo.getBaseUrl());
        return result;
    }

    @Override
    public LinkUrlBo queryLinkUrl(String shortCode) {
        LinkUrlBo linkUrlBo = new LinkUrlBo();
        String sql="select * from LinkUrl where code=?";
        JdbcUtil u=new JdbcUtil();
        ResultSet rs=u.Query(sql,shortCode);
        try {
            while(rs.next()){
                linkUrlBo.setBaseUrl(rs.getString("baseUrl"));
                linkUrlBo.setCode(rs.getString("code"));
                linkUrlBo.setCount(rs.getInt("count"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         return linkUrlBo;
    }
}
