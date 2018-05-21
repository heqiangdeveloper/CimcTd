package com.cimcitech.cimctd.bean;

import java.util.ArrayList;

/**
 * Created by cimcitech on 2017/8/2.
 */

public class AreaVo {

    private ArrayList<Province> data;
    private String msg;
    private boolean success;

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

    public ArrayList<Province> getData() {
        return data;
    }

    public void setData(ArrayList<Province> data) {
        this.data = data;
    }

    public class Province {
        private int categoryid;
        private String categoryname;
        private String categoryno;
        private ArrayList<City> cateList;

        public int getCategoryid() {
            return categoryid;
        }

        public void setCategoryid(int categoryid) {
            this.categoryid = categoryid;
        }

        public String getCategoryname() {
            return categoryname;
        }

        public void setCategoryname(String categoryname) {
            this.categoryname = categoryname;
        }

        public String getCategoryno() {
            return categoryno;
        }

        public void setCategoryno(String categoryno) {
            this.categoryno = categoryno;
        }

        public ArrayList<City> getCateList() {
            return cateList;
        }

        public void setCateList(ArrayList<City> cateList) {
            this.cateList = cateList;
        }

        public class City {
            private int categoryid;
            private String categoryname;
            private String categoryno;

            public int getCategoryid() {
                return categoryid;
            }

            public void setCategoryid(int categoryid) {
                this.categoryid = categoryid;
            }

            public String getCategoryname() {
                return categoryname;
            }

            public void setCategoryname(String categoryname) {
                this.categoryname = categoryname;
            }

            public String getCategoryno() {
                return categoryno;
            }

            public void setCategoryno(String categoryno) {
                this.categoryno = categoryno;
            }
        }
    }
}
