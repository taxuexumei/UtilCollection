package com.zhiyou100.crm.util;

public class Pager {
	// 总共的条数
    int total;
    // 当前页
    int pageNo;
    // 每页获取的数据
    int pageSize;
    // 页码总数
    int pageCount;
    
    int start;
    int end;
    
    // 注意：构建函数重载与this的使用
    public Pager(int total, int pageNo) {
    	this(total, pageNo, 1);
    }
    
    public Pager(int total, int pageNo, int pageSize) {
        this.total = total;
        this.pageNo = pageNo;
        this.start = pageNo;
        this.end = pageNo;
        this.pageSize = pageSize;

        this.pageCount = (int)Math.ceil(this.total * 1.0 / this.pageSize);

        System.out.println("pageCount"+pageCount);

        System.out.println("start"+this.start);
        System.out.println("end"+this.end);

        while ( (this.start > 1 || this.end < this.pageCount)) {
            if (this.start > 1) {
                this.start--;
               
            }
            if (this.end < this.pageCount) {
                this.end++;
              
            }
        }
        
        System.out.println("遍历后"+this.start);
        System.out.println("遍历后"+this.end);
    }
    
    @Override
    public String toString() {
    	return "{" +
				"total:" + this.total + ", " +
				"pageNo:" + this.pageNo + ", " +
				"pageSize:" + this.pageSize + ", " +
				"pageCount:" + this.pageCount + ", " + 
				"start:" + this.start + ", " +
				"end:" + this.end + ", " +
				"}";
    }

    public int getTotal() {
        return this.total;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getPageCount() {
        return this.pageCount;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }
    
}
