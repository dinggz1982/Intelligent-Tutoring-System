package edu.gzhu.its.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import edu.gzhu.its.dao.BaseDAO;

@Repository
public class BaseDAOImpl<T, ID extends Serializable> implements BaseDAO<T, ID> {
	@PersistenceContext
	private EntityManager entityManager;

	private Class<T> clz;
	
	private String className;

	/**
	 * 构造方法，根据实例类自动获取实体类类型
	 */
	@SuppressWarnings("unchecked")
	public BaseDAOImpl() {
		this.clz = null;
		Class<? extends BaseDAOImpl> c = getClass();
		Type t = c.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			this.clz = (Class<T>) p[0];
			this.className = this.clz.getName();
		}
	}

	@Transactional
	@Override
	public boolean save(T entity) {
		boolean flag = false;
		try {
			entityManager.persist(entity);
			flag = true;
		} catch (Exception e) {
			System.out.println("---------------保存出错---------------");
			throw e;
		}
		return flag;
	}

	@Transactional
	@Override
	public T findByid(T t, Long id) {
		return (T) entityManager.find(t.getClass(), id);
	}

	@Transactional
	@Override
	public List<T> findBysql(String tablename, String filed, Object o) {
		String sql = "from " + tablename + " u WHERE u." + filed + "=?";
		System.out.println(sql + "--------sql语句-------------");
		Query query = entityManager.createQuery(sql);
		query.setParameter(1, o);
		List<T> list = query.getResultList();
		entityManager.close();
		return list;
	}

	@Override
	public Object findObjiectBysql(String tablename, String filed, Object o) {
		String sql = "from " + tablename + " u WHERE u." + filed + "=?";
		System.out.println(sql + "--------sql语句-------------");
		Query query = entityManager.createQuery(sql);
		query.setParameter(1, o);

		entityManager.close();
		return query.getSingleResult();
	}

	@Transactional
	@Override
	public List<T> findByMoreFiled(String tablename, LinkedHashMap<String, Object> map) {
		String sql = "from " + tablename + " u WHERE ";
		Set<String> set = null;
		set = map.keySet();
		List<String> list = new ArrayList<>(set);
		List<Object> filedlist = new ArrayList<>();
		for (String filed : list) {
			sql += "u." + filed + "=? and ";
			filedlist.add(filed);
		}
		sql = sql.substring(0, sql.length() - 4);
		System.out.println(sql + "--------sql语句-------------");
		Query query = entityManager.createQuery(sql);
		for (int i = 0; i < filedlist.size(); i++) {
			query.setParameter(i + 1, map.get(filedlist.get(i)));
		}
		List<T> listRe = query.getResultList();
		entityManager.close();
		return listRe;
	}

	@Transactional
	@Override
	public List<T> findByMoreFiledpages(String tablename, LinkedHashMap<String, Object> map, int start,
			int pageNumber) {
		String sql = "from " + tablename + " u WHERE ";
		Set<String> set = null;
		set = map.keySet();
		List<String> list = new ArrayList<>(set);
		List<Object> filedlist = new ArrayList<>();
		for (String filed : list) {
			sql += "u." + filed + "=? and ";
			filedlist.add(filed);
		}
		sql = sql.substring(0, sql.length() - 4);
		System.out.println(sql + "--------sql语句-------------");
		Query query = entityManager.createQuery(sql);
		for (int i = 0; i < filedlist.size(); i++) {
			query.setParameter(i + 1, map.get(filedlist.get(i)));
		}
		query.setFirstResult((start - 1) * pageNumber);
		query.setMaxResults(pageNumber);
		List<T> listRe = query.getResultList();
		entityManager.close();
		return listRe;
	}

	@Transactional
	@Override
	public List<T> findpages(String tablename, String filed, Object o, int start, int pageNumer) {
		String sql = "from " + tablename + " u WHERE u." + filed + "=?";
		System.out.println(sql + "--------page--sql语句-------------");
		List<T> list = new ArrayList<>();
		try {
			Query query = entityManager.createQuery(sql);
			query.setParameter(1, o);
			query.setFirstResult((start - 1) * pageNumer);
			query.setMaxResults(pageNumer);
			list = query.getResultList();
			entityManager.close();
		} catch (Exception e) {
			System.out.println("------------分页错误---------------");
		}

		return list;
	}

	@Transactional
	@Override
	public boolean update(T entity) {
		boolean flag = false;
		try {
			entityManager.merge(entity);
			flag = true;
		} catch (Exception e) {
			System.out.println("---------------更新出错---------------");
		}
		return flag;
	}

	@Transactional
	@Override
	public Integer updateMoreFiled(String tablename, LinkedHashMap<String, Object> map) {
		String sql = "UPDATE " + tablename + " AS u SET ";
		Set<String> set = null;
		set = map.keySet();
		List<String> list = new ArrayList<>(set);
		for (int i = 0; i < list.size() - 1; i++) {
			if (map.get(list.get(i)).getClass().getTypeName() == "java.lang.String") {
				System.out.println("-*****" + map.get(list.get(i)) + "------------" + list.get(i));
				sql += "u." + list.get(i) + "='" + map.get(list.get(i)) + "' , ";
			} else {
				sql += "u." + list.get(i) + "=" + map.get(list.get(i)) + " , ";
			}
		}
		sql = sql.substring(0, sql.length() - 2);
		sql += "where u.id=? ";
		System.out.println(sql + "--------sql语句-------------");
		int resurlt = 0;
		try {
			Query query = entityManager.createQuery(sql);
			query.setParameter(1, map.get("id"));
			resurlt = query.executeUpdate();
		} catch (Exception e) {
			System.out.println("更新出错-----------------------");
			e.printStackTrace();

		}
		return resurlt;
	}

	@Transactional
	@Override
	public boolean delete(T entity) {
		boolean flag = false;
		try {
			entityManager.remove(entityManager.merge(entity));
			flag = true;
		} catch (Exception e) {
			System.out.println("---------------删除出错---------------");
		}
		return flag;
	}

	@Override
	public Object findCount(String tablename, LinkedHashMap<String, Object> map) {
		String sql = "select count(u) from " + tablename + " u WHERE ";
		Set<String> set = null;
		set = map.keySet();
		List<String> list = new ArrayList<>(set);
		List<Object> filedlist = new ArrayList<>();
		for (String filed : list) {
			sql += "u." + filed + "=? and ";
			filedlist.add(filed);
		}
		sql = sql.substring(0, sql.length() - 4);
		System.out.println(sql + "--------sql语句-------------");
		Query query = entityManager.createQuery(sql);
		for (int i = 0; i < filedlist.size(); i++) {
			query.setParameter(i + 1, map.get(filedlist.get(i)));
		}
		return query.getSingleResult();
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("FROM " + className);
		List<T> result = query.getResultList();
		return result;
	}

}
