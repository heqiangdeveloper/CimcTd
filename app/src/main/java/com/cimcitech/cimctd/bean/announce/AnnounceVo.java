package com.cimcitech.cimctd.bean.announce;

import java.util.List;

/**
 * Created by apple on 2017/8/11.
 */

public class AnnounceVo {
    /**
     * data : {"endRow":6,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"anncontent":null,"annid":80,"announcedesc":"取经问道 集团&车辆板块2017年环保","annstatus":null,"anntitle":"取经问道 集团&车辆板块2017年环保、健康协同交流班 在洛顺利举办","anntitlepic":"http://www.lingyu.com/zhpt/3.jpg","anntype":"CZ01","annurl":null,"createdate":1496223835000,"creator":null,"looktimes":null,"modifiedby":null,"ts":null},{"anncontent":null,"annid":79,"announcedesc":"中集凌宇举办精益ONE模式推进培训交流会","annstatus":null,"anntitle":"中集凌宇举办精益ONE模式推进培训交流会","anntitlepic":"http://www.lingyu.com/zhpt/2.jpg","anntype":"CZ01","annurl":null,"createdate":1496223611000,"creator":null,"looktimes":null,"modifiedby":null,"ts":null},{"anncontent":null,"annid":78,"announcedesc":"洛阳中集凌宇开展2017上半年度消防大演习活动","annstatus":null,"anntitle":"洛阳中集凌宇开展2017上半年度消防大演习活动","anntitlepic":"http://www.lingyu.com/zhpt/1.jpg","anntype":"CZ01","annurl":null,"createdate":1496223411000,"creator":null,"looktimes":null,"modifiedby":null,"ts":null},{"anncontent":null,"annid":76,"announcedesc":"中集凌宇召开HSE年度方针目标解读会议 暨责任书签订仪式","annstatus":null,"anntitle":"中集凌宇召开HSE年度方针目标解读会议 暨责任书签订仪式","anntitlepic":null,"anntype":"CZ01","annurl":null,"createdate":1492459919000,"creator":null,"looktimes":null,"modifiedby":null,"ts":null},{"anncontent":null,"annid":75,"announcedesc":"洛阳中科信息产业研究院\u201c万安众创·博士大讲堂","annstatus":null,"anntitle":"洛阳中科信息产业研究院\u201c万安众创·博士大讲堂\u201d 专题调研团队到访中集凌宇","anntitlepic":null,"anntype":"CZ01","annurl":null,"createdate":1492459872000,"creator":null,"looktimes":null,"modifiedby":null,"ts":null},{"anncontent":null,"annid":74,"announcedesc":"阳春三月，春暖花开，一年中最炫丽多彩的季节如约而至。","annstatus":null,"anntitle":"定格瞬间 发现身边的美--中集凌宇第七届职工摄影比赛圆满结束","anntitlepic":null,"anntype":"CZ01","annurl":null,"createdate":1492110780000,"creator":null,"looktimes":null,"modifiedby":null,"ts":null}],"navigateFirstPage":1,"navigateLastPage":1,"navigatePages":8,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":10,"pages":1,"prePage":0,"size":6,"startRow":1,"total":6}
     * msg :
     * success : true
     */

    private DataBean data;
    private String msg;
    private boolean success;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * endRow : 6
         * firstPage : 1
         * hasNextPage : false
         * hasPreviousPage : false
         * isFirstPage : true
         * isLastPage : true
         * lastPage : 1
         * list : [{"anncontent":null,"annid":80,"announcedesc":"取经问道 集团&车辆板块2017年环保","annstatus":null,"anntitle":"取经问道 集团&车辆板块2017年环保、健康协同交流班 在洛顺利举办","anntitlepic":"http://www.lingyu.com/zhpt/3.jpg","anntype":"CZ01","annurl":null,"createdate":1496223835000,"creator":null,"looktimes":null,"modifiedby":null,"ts":null},{"anncontent":null,"annid":79,"announcedesc":"中集凌宇举办精益ONE模式推进培训交流会","annstatus":null,"anntitle":"中集凌宇举办精益ONE模式推进培训交流会","anntitlepic":"http://www.lingyu.com/zhpt/2.jpg","anntype":"CZ01","annurl":null,"createdate":1496223611000,"creator":null,"looktimes":null,"modifiedby":null,"ts":null},{"anncontent":null,"annid":78,"announcedesc":"洛阳中集凌宇开展2017上半年度消防大演习活动","annstatus":null,"anntitle":"洛阳中集凌宇开展2017上半年度消防大演习活动","anntitlepic":"http://www.lingyu.com/zhpt/1.jpg","anntype":"CZ01","annurl":null,"createdate":1496223411000,"creator":null,"looktimes":null,"modifiedby":null,"ts":null},{"anncontent":null,"annid":76,"announcedesc":"中集凌宇召开HSE年度方针目标解读会议 暨责任书签订仪式","annstatus":null,"anntitle":"中集凌宇召开HSE年度方针目标解读会议 暨责任书签订仪式","anntitlepic":null,"anntype":"CZ01","annurl":null,"createdate":1492459919000,"creator":null,"looktimes":null,"modifiedby":null,"ts":null},{"anncontent":null,"annid":75,"announcedesc":"洛阳中科信息产业研究院\u201c万安众创·博士大讲堂","annstatus":null,"anntitle":"洛阳中科信息产业研究院\u201c万安众创·博士大讲堂\u201d 专题调研团队到访中集凌宇","anntitlepic":null,"anntype":"CZ01","annurl":null,"createdate":1492459872000,"creator":null,"looktimes":null,"modifiedby":null,"ts":null},{"anncontent":null,"annid":74,"announcedesc":"阳春三月，春暖花开，一年中最炫丽多彩的季节如约而至。","annstatus":null,"anntitle":"定格瞬间 发现身边的美--中集凌宇第七届职工摄影比赛圆满结束","anntitlepic":null,"anntype":"CZ01","annurl":null,"createdate":1492110780000,"creator":null,"looktimes":null,"modifiedby":null,"ts":null}]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         * navigatePages : 8
         * navigatepageNums : [1]
         * nextPage : 0
         * pageNum : 1
         * pageSize : 10
         * pages : 1
         * prePage : 0
         * size : 6
         * startRow : 1
         * total : 6
         */

        private int endRow;
        private int firstPage;
        private boolean hasNextPage;
        private boolean hasPreviousPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private int lastPage;
        private int navigateFirstPage;
        private int navigateLastPage;
        private int navigatePages;
        private int nextPage;
        private int pageNum;
        private int pageSize;
        private int pages;
        private int prePage;
        private int size;
        private int startRow;
        private int total;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * anncontent : null
             * annid : 80
             * announcedesc : 取经问道 集团&车辆板块2017年环保
             * annstatus : null
             * anntitle : 取经问道 集团&车辆板块2017年环保、健康协同交流班 在洛顺利举办
             * anntitlepic : http://www.lingyu.com/zhpt/3.jpg
             * anntype : CZ01
             * annurl : null
             * createdate : 1496223835000
             * creator : null
             * looktimes : null
             * modifiedby : null
             * ts : null
             */

            private Object anncontent;
            private int annid;
            private String announcedesc;
            private Object annstatus;
            private String anntitle;
            private String anntitlepic;
            private String anntype;
            private Object annurl;
            private long createdate;
            private Object creator;
            private Object looktimes;
            private Object modifiedby;
            private Object ts;

            public Object getAnncontent() {
                return anncontent;
            }

            public void setAnncontent(Object anncontent) {
                this.anncontent = anncontent;
            }

            public int getAnnid() {
                return annid;
            }

            public void setAnnid(int annid) {
                this.annid = annid;
            }

            public String getAnnouncedesc() {
                return announcedesc;
            }

            public void setAnnouncedesc(String announcedesc) {
                this.announcedesc = announcedesc;
            }

            public Object getAnnstatus() {
                return annstatus;
            }

            public void setAnnstatus(Object annstatus) {
                this.annstatus = annstatus;
            }

            public String getAnntitle() {
                return anntitle;
            }

            public void setAnntitle(String anntitle) {
                this.anntitle = anntitle;
            }

            public String getAnntitlepic() {
                return anntitlepic;
            }

            public void setAnntitlepic(String anntitlepic) {
                this.anntitlepic = anntitlepic;
            }

            public String getAnntype() {
                return anntype;
            }

            public void setAnntype(String anntype) {
                this.anntype = anntype;
            }

            public Object getAnnurl() {
                return annurl;
            }

            public void setAnnurl(Object annurl) {
                this.annurl = annurl;
            }

            public long getCreatedate() {
                return createdate;
            }

            public void setCreatedate(long createdate) {
                this.createdate = createdate;
            }

            public Object getCreator() {
                return creator;
            }

            public void setCreator(Object creator) {
                this.creator = creator;
            }

            public Object getLooktimes() {
                return looktimes;
            }

            public void setLooktimes(Object looktimes) {
                this.looktimes = looktimes;
            }

            public Object getModifiedby() {
                return modifiedby;
            }

            public void setModifiedby(Object modifiedby) {
                this.modifiedby = modifiedby;
            }

            public Object getTs() {
                return ts;
            }

            public void setTs(Object ts) {
                this.ts = ts;
            }
        }
    }
}
