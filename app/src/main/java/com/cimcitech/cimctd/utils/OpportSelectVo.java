package com.cimcitech.cimctd.utils;

import java.util.List;

/**
 * Created by cimcitech on 2017/8/7.
 */

public class OpportSelectVo {

    /**
     * data : {"product":[{"active":null,"codeValueList":[{"active":null,"codeValueList":[{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5250GRY","codevalue":"易燃液体罐式运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5251GRY","codevalue":"易燃液体罐式运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5251GYY","codevalue":"运油车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5251GYYA","codevalue":"运油车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5260GYY","codevalue":"铝合金运油车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5310GRY","codevalue":"易燃液体罐式运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5310GYYA","codevalue":"铝合金运油车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5310GYYB","codevalue":"运油车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5310GYYC","codevalue":"铝合金运油车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"Y000","codevalue":"液罐单车通用车型","ecodevalue":null,"itemid":null,"parentid":null}],"codeid":null,"codetype":"PV01","codevalue":"液罐单车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":[{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9300GYY","codevalue":"铝合金运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9340GRY","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9340GYYB","codevalue":"运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9341GYYB","codevalue":"铝合金运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9350GRY","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9350GRYA","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9350GYY","codevalue":"运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9350GYYA","codevalue":"运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9351GYYA","codevalue":"铝合金运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9351GYYC","codevalue":"铝合金运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9400GFW","codevalue":"腐蚀性物品罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9400GFWA","codevalue":"腐蚀性物品罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9400GLY","codevalue":"沥青运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9400GRH","codevalue":"润滑油罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9400GSY","codevalue":"食用油运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9400GYW","codevalue":"氧化性物品罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GFW","codevalue":"腐蚀性物品罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GFWA","codevalue":"腐蚀性物品罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GFWB","codevalue":"腐蚀性物品罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GLY","codevalue":"沥青运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRY","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYA","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYB","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYC","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYD","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYE","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYF","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYG","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYH","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYK","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYL","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYM","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYN","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYP","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYQ","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYS1","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GSY","codevalue":"食用油运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9402GFW","codevalue":"腐蚀性物品罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9402GRY","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9402GSY","codevalue":"铝合金食用油运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9403GRY","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9403GRYA","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9403GRYC","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9403GSY","codevalue":"铝合金食用油运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9404GRY","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9404GRYA","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9404GRYB","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9404GRYC","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9404GRYE","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9404GRYF","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9404GSY","codevalue":"铝合金食用油运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9404GYYA","codevalue":"运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9404GYYB","codevalue":"运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9404GYYC","codevalue":"运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9405GRY","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9405GSY","codevalue":"食用油运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9405GYYA","codevalue":"铝合金运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9405GYYB","codevalue":"铝合金运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9405GYYC","codevalue":"铝合金运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9406GRY","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9406GRYB","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9406GRYC","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9406GRYD","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9406GSY","codevalue":"铝合金食用油运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9407GRY","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9407GYY","codevalue":"铝合金运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9407GYY1","codevalue":"铝合金运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9407GYY2","codevalue":"铝合金运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9407GYYB","codevalue":"铝合金运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9407GYYC","codevalue":"铝合金运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRY","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRYA","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRYB","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRYC","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRYD","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRYF","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRYG","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRYH","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRYK","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRYL","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRYM","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRYP","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRYQ","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"Z000","codevalue":"液罐半挂通用车型","ecodevalue":null,"itemid":null,"parentid":null}],"codeid":null,"codetype":"PV02","codevalue":"液罐半挂","ecodevalue":null,"itemid":null,"parentid":null}],"codeid":null,"codetype":"PC01","codevalue":"液罐车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":[{"active":null,"codeValueList":[{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5120ZBG","codevalue":"背罐车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5250GFLA11","codevalue":"低密度粉粒物料运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5250GFLA12","codevalue":"低密度粉粒物料运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5250GFLA13","codevalue":"低密度粉粒物料运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5250GFLCA","codevalue":"低密度粉粒物料运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5250GFLCA5","codevalue":"低密度粉粒物料运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5250GGHCA","codevalue":"干混砂浆运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5250GXHA11","codevalue":"下灰车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5250GXHA13","codevalue":"下灰车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5250GXHCA","codevalue":"下灰车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5250GXHCA5","codevalue":"下灰车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5257GFLZZ","codevalue":"低密度粉粒物料运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5257GXHZZ","codevalue":"下灰车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5310GFLCA","codevalue":"低密度粉粒物料运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5310GFLCA1","codevalue":"低密度粉粒物料运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5310GXHCA","codevalue":"下灰车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5310GXHCA1","codevalue":"下灰车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5311GFLA13","codevalue":"低密度粉粒物料运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5311GFLA9","codevalue":"低密度粉粒物料运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5311GFLSQR","codevalue":"低密度粉粒物料运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5311GXHA9","codevalue":"下灰车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5311GXHSQR","codevalue":"下灰车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5313GFLBJ","codevalue":"低密度粉粒物料运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5313GXHBJ","codevalue":"下灰车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5315GXHCQ","codevalue":"下灰车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5316GFLSX","codevalue":"低密度粉粒物料运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5316GXHSX","codevalue":"下灰车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5317GFLZZ","codevalue":"低密度粉粒物料运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5317GFLZZ1","codevalue":"低密度粉粒物料运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5317GXHZZ","codevalue":"下灰车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5317GXHZZ1","codevalue":"下灰车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5319GXHBJ","codevalue":"下灰车","ecodevalue":null,"itemid":null,"parentid":null}],"codeid":null,"codetype":"PV03","codevalue":"散装单车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":[{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9400GFL","codevalue":"中密度粉粒物料运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9400GXH","codevalue":"下灰半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9400ZLS","codevalue":"散装粮食运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GFL","codevalue":"中密度粉粒物料运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GFL1","codevalue":"中密度粉粒物料运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GXH","codevalue":"下灰半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9402GFL","codevalue":"粉粒物料运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9402GFLA","codevalue":"中密度粉粒物料运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9403GFL","codevalue":"低密度粉粒物料运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9403GXH","codevalue":"下灰半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9404GFL","codevalue":"低密度粉粒物料运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9404GXH","codevalue":"下灰半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9405GFL","codevalue":"低密度粉粒物料运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9405GFLA","codevalue":"低密度粉粒物料运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9405GFLB","codevalue":"低密度粉粒物料运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9405GFLC","codevalue":"铝合金中密度粉粒物料运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9405GXH1","codevalue":"下灰半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9406GFL","codevalue":"低密度粉粒物料运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9406GXH1","codevalue":"下灰半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9407GXH","codevalue":"下灰半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GXH","codevalue":"下灰半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9409GFL","codevalue":"铝合金中密度粉粒物料运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9409GFLA1","codevalue":"中密度粉粒物料运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9409GFLB","codevalue":"低密度粉粒物料运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9409GXH","codevalue":"下灰半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"ZJV9400GFLLYC","codevalue":"低密度粉粒物料运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"ZJV9401GFLLYA","codevalue":"中密度粉粒物料运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"ZJV9401GFLLYB","codevalue":"中密度粉粒物料运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"ZJV9403GFLLY","codevalue":"粉粒物料运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"ZJV9404GFLLY","codevalue":"中密度粉粒物料运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"ZJV9407GFLLY1","codevalue":"低密度粉粒物料运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"ZJV9408GFLLY","codevalue":"粉粒物料运输半挂车","ecodevalue":null,"itemid":null,"parentid":null}],"codeid":null,"codetype":"PV04","codevalue":"散装半挂","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":[{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5168GJB","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5250GJBYCE5","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5251GJB3","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5254GJBSX1","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5254GJBSX2","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5255GJB4","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5255GJB43E5","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5255GJB4L1","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5257GJB43E1","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5257GJB43E1L","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5257GJB6","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5257GJB7","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5257GJB8","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5257GJB9","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5258GJB8","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5310GJB3","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5314GJB1","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5314GJB2","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5315GJB1","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5315GJB36E5","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5317GJB36E1","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5317GJB5","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5318GJB1","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5318GJB36E5","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"ZJV5257GJBLYZZ7","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"ZJV5259GJBLYDF1","codevalue":"混凝土搅拌运输车","ecodevalue":null,"itemid":null,"parentid":null}],"codeid":null,"codetype":"PV05","codevalue":"搅拌车","ecodevalue":null,"itemid":null,"parentid":null}],"codeid":null,"codetype":"PC02","codevalue":"工程车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":[{"active":null,"codeValueList":[{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5022ZXXE5","codevalue":"车厢可卸式垃圾车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5023ZDJE5","codevalue":"压缩式对接垃圾车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5031ZDJE5","codevalue":"压缩式对接垃圾车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5031ZXXE5","codevalue":"车厢可卸式垃圾车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5070TCAE5","codevalue":"餐厨垃圾车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5071ZYSNJE5","codevalue":"压缩式垃圾车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5071ZZZE5","codevalue":"自装卸式垃圾车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5072GXWE5","codevalue":"吸污车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5072ZYSHFE5","codevalue":"压缩式垃圾车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5073ZYSQLE5","codevalue":"压缩式垃圾车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5074ZXXE5","codevalue":"车厢可卸式垃圾车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5076ZXXE5","codevalue":"车厢可卸式垃圾车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5077ZXXE5","codevalue":"车厢可卸式垃圾车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5122ZXXE5","codevalue":"车厢可卸式垃圾车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5123ZDJE5","codevalue":"压缩式对接垃圾车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5161ZYSDFE5","codevalue":"压缩式垃圾车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5162GXWE5","codevalue":"吸污车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5162ZDJE5","codevalue":"压缩式对接垃圾车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5162ZYSEQN5","codevalue":"压缩式垃圾车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5165ZXXE5","codevalue":"车厢可卸式垃圾车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5166ZXXN5","codevalue":"车厢可卸式垃圾车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5255ZXXE5","codevalue":"车厢可卸式垃圾车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5257ZXXE5","codevalue":"车厢可卸式垃圾车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5258ZXXN5","codevalue":"车厢可卸式垃圾车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5312ZXXE5","codevalue":"车厢可卸式垃圾车","ecodevalue":null,"itemid":null,"parentid":null}],"codeid":null,"codetype":"PV09","codevalue":"环卫车","ecodevalue":null,"itemid":null,"parentid":null}],"codeid":null,"codetype":"PC03","codevalue":"环卫车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":[{"active":null,"codeValueList":[{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5040XLC","codevalue":"冷藏车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5310XLC","codevalue":"冷藏车","ecodevalue":null,"itemid":null,"parentid":null}],"codeid":null,"codetype":"PV08","codevalue":"冷藏车","ecodevalue":null,"itemid":null,"parentid":null}],"codeid":null,"codetype":"PC05","codevalue":"冷藏车","ecodevalue":null,"itemid":null,"parentid":null}],"possibilty":[{"active":null,"codeValueList":null,"codeid":"PS01","codetype":null,"codevalue":"低","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":"PS02","codetype":null,"codevalue":"中","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":"PS03","codetype":null,"codevalue":"高","ecodevalue":null,"itemid":null,"parentid":null}],"payMethod":[{"active":null,"codeValueList":null,"codeid":"PAM01","codetype":null,"codevalue":"全款","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":"PAM02","codetype":null,"codevalue":"按揭","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":"PAM03","codetype":null,"codevalue":"分期付款","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":"PAM04","codetype":null,"codevalue":"其他","ecodevalue":null,"itemid":null,"parentid":null}],"currency":[{"active":null,"codeValueList":null,"codeid":"RMB","codetype":null,"codevalue":"人民币","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":"USD","codetype":null,"codevalue":"美元","ecodevalue":null,"itemid":null,"parentid":null}],"follow":[{"active":null,"codeValueList":null,"codeid":"FOLLOW01","codetype":null,"codevalue":"当面拜访","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":"FOLLOW02","codetype":null,"codevalue":"电话沟通","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":"FOLLOW03","codetype":null,"codevalue":"邮件沟通","ecodevalue":null,"itemid":null,"parentid":null}],"opportType":[{"active":null,"codeValueList":null,"codeid":"OT01","codetype":null,"codevalue":"正常订单","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":"OT02","codetype":null,"codevalue":"4S店订单","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":"OT03","codetype":null,"codevalue":"大委改","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":"OT04","codetype":null,"codevalue":"预排产单","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":"OT05","codetype":null,"codevalue":"信贷订单","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":"OT06","codetype":null,"codevalue":"样车","ecodevalue":null,"itemid":null,"parentid":null}]}
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
        private List<ProductBean> product;
        private List<PossibiltyBean> possibilty;
        private List<PayMethodBean> payMethod;
        private List<CurrencyBean> currency;
        private List<FollowBean> follow;
        private List<OpportTypeBean> opportType;

        public List<ProductBean> getProduct() {
            return product;
        }

        public void setProduct(List<ProductBean> product) {
            this.product = product;
        }

        public List<PossibiltyBean> getPossibilty() {
            return possibilty;
        }

        public void setPossibilty(List<PossibiltyBean> possibilty) {
            this.possibilty = possibilty;
        }

        public List<PayMethodBean> getPayMethod() {
            return payMethod;
        }

        public void setPayMethod(List<PayMethodBean> payMethod) {
            this.payMethod = payMethod;
        }

        public List<CurrencyBean> getCurrency() {
            return currency;
        }

        public void setCurrency(List<CurrencyBean> currency) {
            this.currency = currency;
        }

        public List<FollowBean> getFollow() {
            return follow;
        }

        public void setFollow(List<FollowBean> follow) {
            this.follow = follow;
        }

        public List<OpportTypeBean> getOpportType() {
            return opportType;
        }

        public void setOpportType(List<OpportTypeBean> opportType) {
            this.opportType = opportType;
        }

        public static class ProductBean {
            /**
             * active : null
             * codeValueList : [{"active":null,"codeValueList":[{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5250GRY","codevalue":"易燃液体罐式运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5251GRY","codevalue":"易燃液体罐式运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5251GYY","codevalue":"运油车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5251GYYA","codevalue":"运油车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5260GYY","codevalue":"铝合金运油车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5310GRY","codevalue":"易燃液体罐式运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5310GYYA","codevalue":"铝合金运油车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5310GYYB","codevalue":"运油车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5310GYYC","codevalue":"铝合金运油车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"Y000","codevalue":"液罐单车通用车型","ecodevalue":null,"itemid":null,"parentid":null}],"codeid":null,"codetype":"PV01","codevalue":"液罐单车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":[{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9300GYY","codevalue":"铝合金运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9340GRY","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9340GYYB","codevalue":"运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9341GYYB","codevalue":"铝合金运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9350GRY","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9350GRYA","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9350GYY","codevalue":"运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9350GYYA","codevalue":"运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9351GYYA","codevalue":"铝合金运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9351GYYC","codevalue":"铝合金运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9400GFW","codevalue":"腐蚀性物品罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9400GFWA","codevalue":"腐蚀性物品罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9400GLY","codevalue":"沥青运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9400GRH","codevalue":"润滑油罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9400GSY","codevalue":"食用油运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9400GYW","codevalue":"氧化性物品罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GFW","codevalue":"腐蚀性物品罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GFWA","codevalue":"腐蚀性物品罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GFWB","codevalue":"腐蚀性物品罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GLY","codevalue":"沥青运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRY","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYA","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYB","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYC","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYD","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYE","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYF","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYG","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYH","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYK","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYL","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYM","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYN","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYP","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYQ","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GRYS1","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9401GSY","codevalue":"食用油运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9402GFW","codevalue":"腐蚀性物品罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9402GRY","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9402GSY","codevalue":"铝合金食用油运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9403GRY","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9403GRYA","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9403GRYC","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9403GSY","codevalue":"铝合金食用油运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9404GRY","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9404GRYA","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9404GRYB","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9404GRYC","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9404GRYE","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9404GRYF","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9404GSY","codevalue":"铝合金食用油运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9404GYYA","codevalue":"运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9404GYYB","codevalue":"运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9404GYYC","codevalue":"运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9405GRY","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9405GSY","codevalue":"食用油运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9405GYYA","codevalue":"铝合金运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9405GYYB","codevalue":"铝合金运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9405GYYC","codevalue":"铝合金运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9406GRY","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9406GRYB","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9406GRYC","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9406GRYD","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9406GSY","codevalue":"铝合金食用油运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9407GRY","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9407GYY","codevalue":"铝合金运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9407GYY1","codevalue":"铝合金运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9407GYY2","codevalue":"铝合金运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9407GYYB","codevalue":"铝合金运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9407GYYC","codevalue":"铝合金运油半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRY","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRYA","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRYB","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRYC","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRYD","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRYF","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRYG","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRYH","codevalue":"铝合金易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRYK","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRYL","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRYM","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRYP","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY9408GRYQ","codevalue":"易燃液体罐式运输半挂车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"Z000","codevalue":"液罐半挂通用车型","ecodevalue":null,"itemid":null,"parentid":null}],"codeid":null,"codetype":"PV02","codevalue":"液罐半挂","ecodevalue":null,"itemid":null,"parentid":null}]
             * codeid : null
             * codetype : PC01
             * codevalue : 液罐车
             * ecodevalue : null
             * itemid : null
             * parentid : null
             */

            private Object active;
            private Object codeid;
            private String codetype;
            private String codevalue;
            private Object ecodevalue;
            private Object itemid;
            private Object parentid;
            private List<CodeValueListBeanX> codeValueList;

            public Object getActive() {
                return active;
            }

            public void setActive(Object active) {
                this.active = active;
            }

            public Object getCodeid() {
                return codeid;
            }

            public void setCodeid(Object codeid) {
                this.codeid = codeid;
            }

            public String getCodetype() {
                return codetype;
            }

            public void setCodetype(String codetype) {
                this.codetype = codetype;
            }

            public String getCodevalue() {
                return codevalue;
            }

            public void setCodevalue(String codevalue) {
                this.codevalue = codevalue;
            }

            public Object getEcodevalue() {
                return ecodevalue;
            }

            public void setEcodevalue(Object ecodevalue) {
                this.ecodevalue = ecodevalue;
            }

            public Object getItemid() {
                return itemid;
            }

            public void setItemid(Object itemid) {
                this.itemid = itemid;
            }

            public Object getParentid() {
                return parentid;
            }

            public void setParentid(Object parentid) {
                this.parentid = parentid;
            }

            public List<CodeValueListBeanX> getCodeValueList() {
                return codeValueList;
            }

            public void setCodeValueList(List<CodeValueListBeanX> codeValueList) {
                this.codeValueList = codeValueList;
            }

            public static class CodeValueListBeanX {
                /**
                 * active : null
                 * codeValueList : [{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5250GRY","codevalue":"易燃液体罐式运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5251GRY","codevalue":"易燃液体罐式运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5251GYY","codevalue":"运油车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5251GYYA","codevalue":"运油车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5260GYY","codevalue":"铝合金运油车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5310GRY","codevalue":"易燃液体罐式运输车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5310GYYA","codevalue":"铝合金运油车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5310GYYB","codevalue":"运油车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"CLY5310GYYC","codevalue":"铝合金运油车","ecodevalue":null,"itemid":null,"parentid":null},{"active":null,"codeValueList":null,"codeid":null,"codetype":"Y000","codevalue":"液罐单车通用车型","ecodevalue":null,"itemid":null,"parentid":null}]
                 * codeid : null
                 * codetype : PV01
                 * codevalue : 液罐单车
                 * ecodevalue : null
                 * itemid : null
                 * parentid : null
                 */

                private Object active;
                private Object codeid;
                private String codetype;
                private String codevalue;
                private Object ecodevalue;
                private Object itemid;
                private Object parentid;
                private List<CodeValueListBean> codeValueList;

                public Object getActive() {
                    return active;
                }

                public void setActive(Object active) {
                    this.active = active;
                }

                public Object getCodeid() {
                    return codeid;
                }

                public void setCodeid(Object codeid) {
                    this.codeid = codeid;
                }

                public String getCodetype() {
                    return codetype;
                }

                public void setCodetype(String codetype) {
                    this.codetype = codetype;
                }

                public String getCodevalue() {
                    return codevalue;
                }

                public void setCodevalue(String codevalue) {
                    this.codevalue = codevalue;
                }

                public Object getEcodevalue() {
                    return ecodevalue;
                }

                public void setEcodevalue(Object ecodevalue) {
                    this.ecodevalue = ecodevalue;
                }

                public Object getItemid() {
                    return itemid;
                }

                public void setItemid(Object itemid) {
                    this.itemid = itemid;
                }

                public Object getParentid() {
                    return parentid;
                }

                public void setParentid(Object parentid) {
                    this.parentid = parentid;
                }

                public List<CodeValueListBean> getCodeValueList() {
                    return codeValueList;
                }

                public void setCodeValueList(List<CodeValueListBean> codeValueList) {
                    this.codeValueList = codeValueList;
                }

                public static class CodeValueListBean {
                    /**
                     * active : null
                     * codeValueList : null
                     * codeid : null
                     * codetype : CLY5250GRY
                     * codevalue : 易燃液体罐式运输车
                     * ecodevalue : null
                     * itemid : null
                     * parentid : null
                     */

                    private Object active;
                    private Object codeValueList;
                    private Object codeid;
                    private String codetype;
                    private String codevalue;
                    private Object ecodevalue;
                    private Object itemid;
                    private Object parentid;

                    public Object getActive() {
                        return active;
                    }

                    public void setActive(Object active) {
                        this.active = active;
                    }

                    public Object getCodeValueList() {
                        return codeValueList;
                    }

                    public void setCodeValueList(Object codeValueList) {
                        this.codeValueList = codeValueList;
                    }

                    public Object getCodeid() {
                        return codeid;
                    }

                    public void setCodeid(Object codeid) {
                        this.codeid = codeid;
                    }

                    public String getCodetype() {
                        return codetype;
                    }

                    public void setCodetype(String codetype) {
                        this.codetype = codetype;
                    }

                    public String getCodevalue() {
                        return codevalue;
                    }

                    public void setCodevalue(String codevalue) {
                        this.codevalue = codevalue;
                    }

                    public Object getEcodevalue() {
                        return ecodevalue;
                    }

                    public void setEcodevalue(Object ecodevalue) {
                        this.ecodevalue = ecodevalue;
                    }

                    public Object getItemid() {
                        return itemid;
                    }

                    public void setItemid(Object itemid) {
                        this.itemid = itemid;
                    }

                    public Object getParentid() {
                        return parentid;
                    }

                    public void setParentid(Object parentid) {
                        this.parentid = parentid;
                    }
                }
            }
        }

        public static class PossibiltyBean {
            /**
             * active : null
             * codeValueList : null
             * codeid : PS01
             * codetype : null
             * codevalue : 低
             * ecodevalue : null
             * itemid : null
             * parentid : null
             */

            private Object active;
            private Object codeValueList;
            private String codeid;
            private Object codetype;
            private String codevalue;
            private Object ecodevalue;
            private Object itemid;
            private Object parentid;

            public Object getActive() {
                return active;
            }

            public void setActive(Object active) {
                this.active = active;
            }

            public Object getCodeValueList() {
                return codeValueList;
            }

            public void setCodeValueList(Object codeValueList) {
                this.codeValueList = codeValueList;
            }

            public String getCodeid() {
                return codeid;
            }

            public void setCodeid(String codeid) {
                this.codeid = codeid;
            }

            public Object getCodetype() {
                return codetype;
            }

            public void setCodetype(Object codetype) {
                this.codetype = codetype;
            }

            public String getCodevalue() {
                return codevalue;
            }

            public void setCodevalue(String codevalue) {
                this.codevalue = codevalue;
            }

            public Object getEcodevalue() {
                return ecodevalue;
            }

            public void setEcodevalue(Object ecodevalue) {
                this.ecodevalue = ecodevalue;
            }

            public Object getItemid() {
                return itemid;
            }

            public void setItemid(Object itemid) {
                this.itemid = itemid;
            }

            public Object getParentid() {
                return parentid;
            }

            public void setParentid(Object parentid) {
                this.parentid = parentid;
            }
        }

        public static class PayMethodBean {
            /**
             * active : null
             * codeValueList : null
             * codeid : PAM01
             * codetype : null
             * codevalue : 全款
             * ecodevalue : null
             * itemid : null
             * parentid : null
             */

            private Object active;
            private Object codeValueList;
            private String codeid;
            private Object codetype;
            private String codevalue;
            private Object ecodevalue;
            private Object itemid;
            private Object parentid;

            public Object getActive() {
                return active;
            }

            public void setActive(Object active) {
                this.active = active;
            }

            public Object getCodeValueList() {
                return codeValueList;
            }

            public void setCodeValueList(Object codeValueList) {
                this.codeValueList = codeValueList;
            }

            public String getCodeid() {
                return codeid;
            }

            public void setCodeid(String codeid) {
                this.codeid = codeid;
            }

            public Object getCodetype() {
                return codetype;
            }

            public void setCodetype(Object codetype) {
                this.codetype = codetype;
            }

            public String getCodevalue() {
                return codevalue;
            }

            public void setCodevalue(String codevalue) {
                this.codevalue = codevalue;
            }

            public Object getEcodevalue() {
                return ecodevalue;
            }

            public void setEcodevalue(Object ecodevalue) {
                this.ecodevalue = ecodevalue;
            }

            public Object getItemid() {
                return itemid;
            }

            public void setItemid(Object itemid) {
                this.itemid = itemid;
            }

            public Object getParentid() {
                return parentid;
            }

            public void setParentid(Object parentid) {
                this.parentid = parentid;
            }
        }

        public static class CurrencyBean {
            /**
             * active : null
             * codeValueList : null
             * codeid : RMB
             * codetype : null
             * codevalue : 人民币
             * ecodevalue : null
             * itemid : null
             * parentid : null
             */

            private Object active;
            private Object codeValueList;
            private String codeid;
            private Object codetype;
            private String codevalue;
            private Object ecodevalue;
            private Object itemid;
            private Object parentid;

            public Object getActive() {
                return active;
            }

            public void setActive(Object active) {
                this.active = active;
            }

            public Object getCodeValueList() {
                return codeValueList;
            }

            public void setCodeValueList(Object codeValueList) {
                this.codeValueList = codeValueList;
            }

            public String getCodeid() {
                return codeid;
            }

            public void setCodeid(String codeid) {
                this.codeid = codeid;
            }

            public Object getCodetype() {
                return codetype;
            }

            public void setCodetype(Object codetype) {
                this.codetype = codetype;
            }

            public String getCodevalue() {
                return codevalue;
            }

            public void setCodevalue(String codevalue) {
                this.codevalue = codevalue;
            }

            public Object getEcodevalue() {
                return ecodevalue;
            }

            public void setEcodevalue(Object ecodevalue) {
                this.ecodevalue = ecodevalue;
            }

            public Object getItemid() {
                return itemid;
            }

            public void setItemid(Object itemid) {
                this.itemid = itemid;
            }

            public Object getParentid() {
                return parentid;
            }

            public void setParentid(Object parentid) {
                this.parentid = parentid;
            }
        }

        public static class FollowBean {
            /**
             * active : null
             * codeValueList : null
             * codeid : FOLLOW01
             * codetype : null
             * codevalue : 当面拜访
             * ecodevalue : null
             * itemid : null
             * parentid : null
             */

            private Object active;
            private Object codeValueList;
            private String codeid;
            private Object codetype;
            private String codevalue;
            private Object ecodevalue;
            private Object itemid;
            private Object parentid;

            public Object getActive() {
                return active;
            }

            public void setActive(Object active) {
                this.active = active;
            }

            public Object getCodeValueList() {
                return codeValueList;
            }

            public void setCodeValueList(Object codeValueList) {
                this.codeValueList = codeValueList;
            }

            public String getCodeid() {
                return codeid;
            }

            public void setCodeid(String codeid) {
                this.codeid = codeid;
            }

            public Object getCodetype() {
                return codetype;
            }

            public void setCodetype(Object codetype) {
                this.codetype = codetype;
            }

            public String getCodevalue() {
                return codevalue;
            }

            public void setCodevalue(String codevalue) {
                this.codevalue = codevalue;
            }

            public Object getEcodevalue() {
                return ecodevalue;
            }

            public void setEcodevalue(Object ecodevalue) {
                this.ecodevalue = ecodevalue;
            }

            public Object getItemid() {
                return itemid;
            }

            public void setItemid(Object itemid) {
                this.itemid = itemid;
            }

            public Object getParentid() {
                return parentid;
            }

            public void setParentid(Object parentid) {
                this.parentid = parentid;
            }
        }

        public static class OpportTypeBean {
            /**
             * active : null
             * codeValueList : null
             * codeid : OT01
             * codetype : null
             * codevalue : 正常订单
             * ecodevalue : null
             * itemid : null
             * parentid : null
             */

            private Object active;
            private Object codeValueList;
            private String codeid;
            private Object codetype;
            private String codevalue;
            private Object ecodevalue;
            private Object itemid;
            private Object parentid;

            public Object getActive() {
                return active;
            }

            public void setActive(Object active) {
                this.active = active;
            }

            public Object getCodeValueList() {
                return codeValueList;
            }

            public void setCodeValueList(Object codeValueList) {
                this.codeValueList = codeValueList;
            }

            public String getCodeid() {
                return codeid;
            }

            public void setCodeid(String codeid) {
                this.codeid = codeid;
            }

            public Object getCodetype() {
                return codetype;
            }

            public void setCodetype(Object codetype) {
                this.codetype = codetype;
            }

            public String getCodevalue() {
                return codevalue;
            }

            public void setCodevalue(String codevalue) {
                this.codevalue = codevalue;
            }

            public Object getEcodevalue() {
                return ecodevalue;
            }

            public void setEcodevalue(Object ecodevalue) {
                this.ecodevalue = ecodevalue;
            }

            public Object getItemid() {
                return itemid;
            }

            public void setItemid(Object itemid) {
                this.itemid = itemid;
            }

            public Object getParentid() {
                return parentid;
            }

            public void setParentid(Object parentid) {
                this.parentid = parentid;
            }
        }
    }
}
