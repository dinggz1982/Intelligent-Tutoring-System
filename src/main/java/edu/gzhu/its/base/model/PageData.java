package edu.gzhu.its.base.model;

import java.util.List;
/**
 * 分页模型
 * <p>Title : PageData</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2017年11月14日 上午12:21:55
 * @param <T>
 */
public class PageData<T> {

	private int pageIndex;
	private int pageSize;
	private int totalCount;
	private List<T> pageData;
	private int startRow;
	private int totalPage;
	
	public PageData(int pageIndex, int pageSize){
		this.pageIndex = pageIndex <= 0 ? 1 : pageIndex;
		this.pageSize = pageSize;
	}
	
	public PageData(){
		
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获得总记录数
	 * @return
	 */
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 获得分页list数据
	 * @return
	 */
	public List<T> getPageData() {
		return pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}

	/**
	 * 获得开始行数
	 * @return
	 */
	public int getStartRow() {
		startRow = (pageIndex-1) * pageSize;
		return startRow;
	}

	/**
	 *  获得总页面数
	 * @return
	 */
	public int getTotalPage() {
		totalPage = (int) Math.ceil(totalCount/Double.parseDouble(String.valueOf(pageSize)));
		return totalPage;
	}
	
	
}
