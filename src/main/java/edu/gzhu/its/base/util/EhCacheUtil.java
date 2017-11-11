package edu.gzhu.its.base.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * EhCache缓存工具
 * <p>Title : EhCache</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2017年11月12日 上午3:49:25
 */
public class EhCacheUtil {
	
	private static final CacheManager cacheManager = new CacheManager();  
    private static Cache cache;  

    public Cache getCache() {  
        return cache;  
    }  
  
    public void setCache(Cache cache) {  
        this.cache = cache;  
    }  
  
  
  
        /* 
     * 通过名称从缓存中获取数据 
     */  
    public static Object getCacheElement(String cacheKey) throws Exception {  
            net.sf.ehcache.Element e = cache.get(cacheKey);  
        if (e == null) {  
            return null;  
        }  
        return e.getValue();  
    }  
    /* 
     * 将对象添加到缓存中 
     */  
    public static void addToCache(String cacheKey, Object result) throws Exception {  
        Element element = new Element(cacheKey, result);  
        cache.put(element);  
    }  

}
