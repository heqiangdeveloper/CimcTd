package com.cimcitech.cimctd.utils;

import com.cimcitech.cimctd.bean.CustSelectVo;

import java.util.ArrayList;

/**
 * Created by cimcitech on 2017/8/2.
 */

public class CustSelectUtils {


    /**
     * 国家
     *
     * @param custSelectVo
     * @param string
     * @return
     */
    public static String getCountryName(CustSelectVo custSelectVo, String string) {
        if (custSelectVo == null && string.equals(""))
            return string;
        for (int i = 0; i < custSelectVo.getData().getRegion().size(); i++) {
            if (string.equals(custSelectVo.getData().getRegion().get(i).getCategoryno())) ;
            return custSelectVo.getData().getRegion().get(i).getCategoryname();
        }
        return string;
    }

    /**
     * 税种
     *
     * @param custSelectVo
     * @param string
     * @return
     */
    public static String getWebName(CustSelectVo custSelectVo, String string) {
        if (custSelectVo == null && string.equals(""))
            return string;
        for (int i = 0; i < custSelectVo.getData().getWeb().size(); i++) {
            if (string.equals(custSelectVo.getData().getWeb().get(i).getCodeid()))
                return custSelectVo.getData().getWeb().get(i).getCodevalue();
        }
        return string;
    }

    /**
     * 客户类型
     *
     * @param custSelectVo
     * @param string
     * @return
     */
    public static String getCustTypeName(CustSelectVo custSelectVo, String string) {
        if (custSelectVo == null && string.equals(""))
            return string;
        for (int i = 0; i < custSelectVo.getData().getCustType().size(); i++) {
            if (string.equals(custSelectVo.getData().getCustType().get(i).getCodeid()))
                return custSelectVo.getData().getCustType().get(i).getCodevalue();
        }
        return string;
    }

    /**
     * 省
     *
     * @param
     * @param
     * @return
     */
    public static String getProvinceName(CustSelectVo custSelectVo, String country, String province) {
        if (custSelectVo == null && country.equals("") && province.equals(""))
            return province;
        ArrayList<CustSelectVo.Data.Region.CateList> cateLists = new ArrayList<>();
        for (int i = 0; i < custSelectVo.getData().getRegion().size(); i++) {
            if (country.equals(custSelectVo.getData().getRegion().get(i).getCategoryno())) {
                cateLists = custSelectVo.getData().getRegion().get(i).getCateList();
            }
        }
        for (int i = 0; i < cateLists.size(); i++) {
            if (province.equals(cateLists.get(i).getCategoryno())) {
                return cateLists.get(i).getCategoryname();
            }
        }
        return province;
    }

    /**
     * 市
     *
     * @param
     * @param
     * @return
     */
    public static String getCityName(CustSelectVo custSelectVo, String country, String province, String city) {

        if (custSelectVo == null && country.equals("") && province.equals("") && city.equals(""))
            return city;

        ArrayList<CustSelectVo.Data.Region.CateList> cateLists = new ArrayList<>();
        ArrayList<CustSelectVo.Data.Region.CateList.City> cities = new ArrayList<>();
        for (int i = 0; i < custSelectVo.getData().getRegion().size(); i++) {
            if (country.equals(custSelectVo.getData().getRegion().get(i).getCategoryno())) {
                cateLists = custSelectVo.getData().getRegion().get(i).getCateList();
            } //省
        }
        for (int i = 0; i < cateLists.size(); i++) {
            if (province.equals(cateLists.get(i).getCategoryno())) {
                cities = cateLists.get(i).getCateList();
            }
        }
        for (int i = 0; i < cities.size(); i++) {
            if (city.equals(cities.get(i).getCategoryno())) {
                return cities.get(i).getCategoryname();
            }
        }
        return city;
    }

    /**
     * 产品类别name
     *
     * @param opportSelectVo
     * @param string
     * @return
     */
    public static String getProductCategory(OpportSelectVo opportSelectVo, String string) {
        if (opportSelectVo != null)
            for (int i = 0; i < opportSelectVo.getData().getProduct().size(); i++)
                if (string.equals(opportSelectVo.getData().getProduct().get(i).getCodetype()))
                    return opportSelectVo.getData().getProduct().get(i).getCodevalue();
        return string;
    }

    /**
     * 产品类别vo
     *
     * @param opportSelectVo
     * @param string
     * @return
     */
    public static OpportSelectVo.DataBean.ProductBean getProductCategoryVo(OpportSelectVo opportSelectVo, String string) {
        if (opportSelectVo != null)
            for (int i = 0; i < opportSelectVo.getData().getProduct().size(); i++)
                if (string.equals(opportSelectVo.getData().getProduct().get(i).getCodetype()))
                    return opportSelectVo.getData().getProduct().get(i);


        return null;
    }


    /**
     * 产品品种name
     *
     * @param opportSelectVo
     * @param string
     * @param str
     * @return
     */

    public static String getProductVariety(OpportSelectVo opportSelectVo, String string, String str) {
        if (opportSelectVo != null)
            for (int i = 0; i < opportSelectVo.getData().getProduct().size(); i++)
                if (string.equals(opportSelectVo.getData().getProduct().get(i).getCodetype()))
                    for (int j = 0; j < opportSelectVo.getData().getProduct().get(i).getCodeValueList().size(); j++)
                        if (str.equals(opportSelectVo.getData().getProduct().get(i).getCodeValueList().get(j).getCodetype()))
                            return opportSelectVo.getData().getProduct().get(i).getCodeValueList().get(j).getCodevalue();
        return str;
    }

    /**
     * 产品品种Vo
     *
     * @param opportSelectVo
     * @param string
     * @param str
     * @return
     */

    public static OpportSelectVo.DataBean.ProductBean.CodeValueListBeanX
    getProductVarietyVo(OpportSelectVo opportSelectVo, String string, String str) {
        if (opportSelectVo != null)
            for (int i = 0; i < opportSelectVo.getData().getProduct().size(); i++)
                if (string.equals(opportSelectVo.getData().getProduct().get(i).getCodetype()))
                    for (int j = 0; j < opportSelectVo.getData().getProduct().get(i).getCodeValueList().size(); j++)
                        if (str.equals(opportSelectVo.getData().getProduct().get(i).getCodeValueList().get(j).getCodetype()))
                            return opportSelectVo.getData().getProduct().get(i).getCodeValueList().get(j);
        return null;
    }

    /**
     * 产品型号name
     *
     * @param opportSelectVo
     * @param string
     * @param str
     * @param s
     * @return
     */
    public static String getProductModel(OpportSelectVo opportSelectVo, String string, String str, String s) {
        if (opportSelectVo != null)
            for (int i = 0; i < opportSelectVo.getData().getProduct().size(); i++)
                if (string.equals(opportSelectVo.getData().getProduct().get(i).getCodetype()))
                    for (int j = 0; j < opportSelectVo.getData().getProduct().get(i).getCodeValueList().size(); j++)
                        if (str.equals(opportSelectVo.getData().getProduct().get(i).getCodeValueList().get(j).getCodetype()))
                            for (int h = 0; h < opportSelectVo.getData().getProduct().get(i).getCodeValueList().get(j)
                                    .getCodeValueList().size(); h++)
                                if (s.equals(opportSelectVo.getData().getProduct().get(i).getCodeValueList().get(j)
                                        .getCodeValueList().get(h).getCodetype()))
                                    return opportSelectVo.getData().getProduct().get(i).getCodeValueList().get(j)
                                            .getCodeValueList().get(h).getCodevalue();
        return s;
    }

    /**
     * 付款方式
     *
     * @param opportSelectVo
     * @param string
     * @return
     */
    public static String getPaymentmethod(OpportSelectVo opportSelectVo, String string) {
        if (opportSelectVo != null)
            for (int i = 0; i < opportSelectVo.getData().getPayMethod().size(); i++)
                if (string.equals(opportSelectVo.getData().getPayMethod().get(i).getCodeid()))
                    return opportSelectVo.getData().getPayMethod().get(i).getCodevalue();
        return string;
    }
}
