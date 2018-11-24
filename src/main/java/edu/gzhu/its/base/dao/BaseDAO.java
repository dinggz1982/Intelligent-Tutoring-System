package edu.gzhu.its.base.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import edu.gzhu.its.base.model.PageData;

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
    List<T> findAll() throws SQLException;
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
     * 根据hql返回list
     * @param hql
     * @return
     */
     List<T> find(String hql);
    
    /**
     * 根据表名，字段，参数查询，拼接sql语句
     * @param  tablename 表名
     * @param filed 字段名
     * @param o 字段参数
     * @return
     */
    List<T> findBysql(String tablename,String filed,Object o);
    
	/**
	 * 根据sql获取一个对象
	 * @param hql
	 * @return
	 */
	public T getByHql(String hql);
    
    /**
     * 获取一个
     * @param filed
     * @param o
     * @return
     */
    public T findOne(String filed, Object o);
    
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
	 * 得到分页数据并封装成pagedata
	 * @param page
	 * @param pageSize
	 * @param hql
	 * @return
	 */
	public PageData<T> getPageData(int page, int pageSize, String hql);
	
	
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
	 * 根據附加的sql查詢
	 * @param start
	 * @param maxSize
	 * @param appendSql
	 * @return
	 */
	public List<T> queryPageData(int start, int maxSize,
			String appendSql);
	
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
	
	 /**
     * 根据原生sql获得结果集
     * 
     * @param sql
     *            SQL语句
     * @return 结果集
     */
    public List<Object> findObjectBySql(String sql);
    
    /**
     * 根据原生sql获得结果集
     * @param sql
     * SQL语句
     * @return 结果集
     */
    public List<Object[]> findBySql(String sql);
    
    /**
     * 根据sql获取count
     * <p>方法名:getCountBySql </p>
     * <p>Description : </p>
     * <p>Company : </p>
     * @author 丁国柱
     * @date 2018年1月31日 上午12:00:02
     * @param sql
     * @return
     */
    public int getCountBySql(String sql);
    
    /**
     * 执行原生sql
     * @param sql
     * @return
     */
    public int executeSql(String sql);
	
}