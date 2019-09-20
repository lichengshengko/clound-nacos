package com.cloud.demo.entity;

import java.io.Serializable;
import javax.persistence.*;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

@Table(name = "user")
public class User implements Serializable {
    /**
     *  
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id", insertable = false, updatable = false)
    @ColumnType(jdbcType=JdbcType.INTEGER)
    private Integer id;

    @ColumnType(jdbcType=JdbcType.VARCHAR)
    private String name;

    private static final long serialVersionUID = 1L;

    /**
     * 获取 
     *
     * @return id -  
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置 
     *
     * @param id  
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}