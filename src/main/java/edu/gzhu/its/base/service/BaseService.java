package edu.gzhu.its.base.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import edu.gzhu.its.base.model.PageData;

public interface BaseService<T,ID extends Serializable> { 
	/**
     * 保存数据对象
     * @param entity
     * @return
     */
    boolean save(T entity);
    /**
     * 根据id查询
     * @param id
     * @return
     */
    T findById(Serializable id);
    /**
     * 根据id查询
     * @param id
     * @return
     */
    T findById(T t,Long id);
    
    /**
     * 查询全部数据
     * @return
     */
    List<T> findAll() throws SQLException;
    /**
     * 根据表名，字段，参数查询，拼接sql语句
     * @param  tablename 表名
     * @param filed 字段名
     * @param o 字段参数
     * @return
     */
    List<T> findBysql(String tablename,String filed,Object o);
    
    /**
     * 获取一个
     * @param filed
     * @param o
     * @return
     */
    public T findOne(String filed, Object o);
    
    /**
	 * 根据sql获取一个对象
	 * @param hql
	 * @return
	 */
	public T getByHql(String hql);
    
    /**
     * 通过原生sql查询
     * <p>方法名:findByNavateSql </p>
     * <p>Description : </p>
     * <p>Company : </p>
     * @author 丁国柱
     * @date 2018年3月6日 下午3:09:27
     * @param sql
     * @return
     */
    List<Object[]> findByNaviteSql(String sql);
    
    
    /**
     * 根据字段，参数查询，拼接sql语句
     * @param  tablename 表名
     * @param filed 字段名
     * @param o 字段参数
     * @return
     */
    List<T> findBySql(String filed,Object o);
    /**
     * 根据字段，参数查询,返回一个参数，拼接sql语句
     * @param  tablename 表名
     * @param filed 字段名
     * @param o 字段参数
     * @return
     */
    T findOneBySql(String filed,Object o);
    
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
    List<T> findByMoreFiledpages(String tablename, LinkedHashMap<String,Object> map, int start, int pageNumer);
    
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
    Object findCount(LinkedHashMap<String,Object> map);
    

	/**
	 * 得到分页数据并封装成pagedata
	 * <p>方法名:getPageData </p>
	 * <p>Description : </p>
	 * <p>Company : </p>
	 * @author 丁国柱
	 * @date 2017年11月14日 上午12:24:50
	 * @param pageIndex
	 * @param pageSize
	 * @param paramMap
	 * @return
	 */
	public PageData<T> getPageData(int pageIndex, int pageSize,
			Map<String, Object> paramMap);
	
	/**
	 * 查询记录数，用于分页
	 * <p>方法名:queryDataCount </p>
	 * <p>Description : </p>
	 * <p>Company : </p>
	 * @author 丁国柱
	 * @date 2017年11月14日 上午12:26:10
	 * @param paramMap
	 * @return
	 */
	public int queryDataCount(Map<String, Object> paramMap);
	
	/**
	 * 根据hql返回查询记录数
	 * <p>方法名:queryPageTotalCount </p>
	 * <p>Description : </p>
	 * <p>Company : </p>
	 * @author 丁国柱
	 * @date 2017年11月14日 上午12:27:04
	 * @param hql
	 * @param params
	 * @return
	 */
	public Long queryPageTotalCount(String hql, final Map<String, Object> params);
	
	/**
	 * 返回分页list
	 * <p>方法名:queryPageData </p>
	 * <p>Description : </p>
	 * <p>Company : </p>
	 * @author 丁国柱
	 * @date 2017年11月14日 上午12:37:36
	 * @param start
	 * @param maxSize
	 * @param paramMap
	 * @return
	 */
	public List<T> queryPageData(int start, int maxSize,
			Map<String, Object> paramMap);
	
	/**
	 * 获得分页LIST
	 * <p>方法名:queryPageList </p>
	 * <p>Description : </p>
	 * <p>Company : </p>
	 * @author 丁国柱
	 * @date 2017年11月14日 上午12:38:50
	 * @param hql
	 * @param params
	 * @param start
	 * @param maxSize
	 * @return
	 */
	public List<T> queryPageList(final String hql,
			final Map<String, Object> params, final int start, final int maxSize);
}