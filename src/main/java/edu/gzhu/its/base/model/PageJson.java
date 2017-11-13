package edu.gzhu.its.base.model;

import java.util.List;

public class PageJson<T> {
	
	private List<T> rows;
	
	private int total;
	
	private int page;
	
	private int records;
	
	private int costtime;

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public int getCosttime() {
		return costtime;
	}

	public void setCosttime(int costtime) {
		this.costtime = costtime;
	}

}
