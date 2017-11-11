package edu.gzhu.its.base;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

public interface BaseDAO<T,ID extends Serializable> { 
	/**
     * 保存数据对象
     * @param entity
     * @return
     */
    boolean save(T entity);
    
    /**
     * 查询全部数据
     * @return
     */
    List<T> findAll();
    /**
     * 根据id查询
     * @param id
     * @param t
     * @return
     */
    T findById(T t,Long id);
    
    /**
     * 根据Id查询
     * @param id
     * @return
     */
    T findById(Serializable id);
    
    /**
     * 根据表名，字段，参数查询，拼接sql语句
     * @param  tablename 表名
     * @param filed 字段名
     * @param o 字段参数
     * @return
     */
    List<T> findBysql(String tablename,String filed,Object o);
    
    /**
     * 根据一个字段查询，返回列表
     * @param filed
     * @param o
     * @return
     */
    List<T> findBySql(String filed,Object o);
    
    /**
     * 根据字段查询，返回一个实体
     * @param filed
     * @param o
     * @return
     */
    T findOneBySql(String filed,Object o);
    
    /**
     * 根据字段查询，返回一个实体
     * @param filed
     * @param o
     * @return
     */
    Object findObjiectBysql(String tablename,String filed,Object o);

    /**
     * 多个字段的查询
     * @param tablename 表名
     * @param map 将你的字段传入map中
     * @return
     */
    List<T> findByMoreFiled(String tablename,LinkedHashMap<String,Object> map);
    
    /**
     * 多个字段的查询
     * @param tablename 表名
     * @param map 将你的字段传入map中
     * @return
     */
    List<T> findByMoreFiled(LinkedHashMap<String,Object> map);
    
    
    /**
     * 多字段查询分页
     * @param tablename 表名
     * @param map 以map存储key,value
     * @param start 第几页
     * @param pageNumer 一个页面的条数
     * @return
     */
    List<T> findByMoreFiledpages(LinkedHashMap<String,Object> map, int start, int pageNumer);

    /**
     * 多字段查询分页
     * @param tablename 表名
     * @param map 以map存储key,value
     * @param start 第几页
     * @param pageNumer 一个页面的条数
     * @return
     */
    List<T> findByMoreFiledpages(String tablename, LinkedHashMap<String,Object> map, int start, int pageNumer);
    /**
     * 一个字段的分页
     * @param  tablename 表名
     * @param filed 字段名
     * @param o 字段参数
     * @param start 第几页
     * @param pageNumer 一个页面多少条数据
     * @return
     */
    List<T> findpages(String tablename,String filed,Object o,int start,int pageNumer);
    /**
     * 根据表的id删除数据
     * @param  entity
     */
    boolean delete(T entity);
    /**
     * 更新对象
     * @param e
     * @return
     */
    boolean update(T e);
    /**
     * 根据传入的map遍历key,value拼接字符串，以id为条件更新
     * @param tablename 表名
     * @param map 传入参数放入map中
     * @return
     */
    Integer updateMoreFiled(String tablename,LinkedHashMap<String,Object> map);


    /**
     * 根据条件查询总条数返回object类型
     * @param tablename  表名
     * @param map 传入参数放入map中
     * @return
     */
    Object findCount(String tablename, LinkedHashMap<String,Object> map);
}