package com.lmxdawn.him.common.vo.req;

/**
 * 分页的请求
 */
public class BaseLimitReqVO extends BaseReqVO{

    /**
     * 页码
     */
    private Integer page;

    /**
     * 数量
     */
    private Integer limit;

    /**
     * 偏移量
     */
    private Integer offset;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    /**
     * 设置偏移量
     */
    public void setOffset() {
        if (null == this.getPage() || this.getPage() <= 0) {
            this.setPage(1);
        }
        if (null == this.getLimit() || this.getLimit() <= 0) {
            this.setLimit(10);
        }
        this.offset = (this.getPage() - 1) * this.getLimit();
    }

}
