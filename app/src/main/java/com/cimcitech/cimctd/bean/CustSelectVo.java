package com.cimcitech.cimctd.bean;

import java.util.ArrayList;

/**
 * Created by cimcitech on 2017/8/1.
 */

public class CustSelectVo {

    private Data data;
    private String msg;
    private boolean success;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
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

    public class Data {

        private ArrayList<Web> web;
        private ArrayList<CustType> custType;
        private ArrayList<Region> region;

        public ArrayList<Web> getWeb() {
            return web;
        }

        public void setWeb(ArrayList<Web> web) {
            this.web = web;
        }

        public ArrayList<CustType> getCustType() {
            return custType;
        }

        public void setCustType(ArrayList<CustType> custType) {
            this.custType = custType;
        }

        public ArrayList<Region> getRegion() {
            return region;
        }

        public void setRegion(ArrayList<Region> region) {
            this.region = region;
        }

        public class Web {
            private String codeid;
            private String codevalue;

            public String getCodeid() {
                return codeid;
            }

            public void setCodeid(String codeid) {
                this.codeid = codeid;
            }

            public String getCodevalue() {
                return codevalue;
            }

            public void setCodevalue(String codevalue) {
                this.codevalue = codevalue;
            }
        }

        public class CustType {
            private String codeid;
            private String codevalue;

            public String getCodeid() {
                return codeid;
            }

            public void setCodeid(String codeid) {
                this.codeid = codeid;
            }

            public String getCodevalue() {
                return codevalue;
            }

            public void setCodevalue(String codevalue) {
                this.codevalue = codevalue;
            }
        }

        public class Region {
            private int categoryid;
            private String categoryname;
            private String categoryno;
            private ArrayList<CateList> cateList;

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

            public ArrayList<CateList> getCateList() {
                return cateList;
            }

            public void setCateList(ArrayList<CateList> cateList) {
                this.cateList = cateList;
            }

            public String getCategoryno() {
                return categoryno;
            }

            public void setCategoryno(String categoryno) {
                this.categoryno = categoryno;
            }

            public class CateList {
                private int categoryid;
                private String categoryname;
                private String categoryno;
                private int superid;
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

                public int getSuperid() {
                    return superid;
                }

                public void setSuperid(int superid) {
                    this.superid = superid;
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
                    private int superid;

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

                    public int getSuperid() {
                        return superid;
                    }

                    public void setSuperid(int superid) {
                        this.superid = superid;
                    }
                }
            }
        }
    }


}
