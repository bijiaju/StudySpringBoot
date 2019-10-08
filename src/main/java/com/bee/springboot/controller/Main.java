package com.bee.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args){
      /*  double x=5.00;
        System.out.println(x+1);
        Long ll = 55l;
        String str=""+ll;
        Integer ss = 55;
        String str1 = ""+ss;
        System.out.println(ll.intValue());
        System.out.println(ss.intValue());
        System.out.println(ss.intValue()==ll.intValue());*/
     // testJosn();
      //  订单 dingdna = new 订单();
        //ArrayList<Integer> integers = Lists.newArrayList(1);
        ArrayList<Integer> integers = null;
        for(int i=0;i<100;i++){
             integers = Lists.newArrayList(i);
            System.out.println(integers);
        }
        System.out.println(integers.size());

    }

    public static void testJosn(){
        HashMap<String,Object> transParam = new HashMap<String,Object>();
        HashMap<String,Object> param = new HashMap<String,Object>();
        param.put("outerOrderId","");
        param.put("contactId","");
        param.put("provCode","");
        param.put("cityCode","");
        param.put("acceptNo","");
        param.put("acceptChannel","");
        param.put("urgentId","");
        param.put("acceptStaffId","");
        param.put("custName","");
        param.put("custLevel","");
        param.put("contactNo","");
        param.put("remarks","");

       // HashMap<String,Object> contactInfo = new HashMap<String,Object>();

        List<ContactInfoBean> contactInfos = new ArrayList<>();
        ContactInfoBean contactInfo = new ContactInfoBean();
        contactInfo.setContactInfoName("");
        contactInfo.setContactInfoPhone("");
        contactInfo.setContactInfoType("");
        contactInfos.add(contactInfo);
        param.put("contactInfo",contactInfos);

        List<SubBusinessInfo> subBusinessInfos = new ArrayList<>();
        SubBusinessInfo subBusinessInfo = new SubBusinessInfo();
        subBusinessInfo.setAppealNo("1");
        subBusinessInfo.setOrderType("1");
        subBusinessInfo.setBusinessId("PERSONAL");
        subBusinessInfo.setBusinessType("P_CTYY");
        subBusinessInfo.setAttachmentPath("");
        HashMap extensionInfo = new HashMap();
        extensionInfo.put("outMoney","");
        extensionInfo.put("outTime","");
        extensionInfo.put("msgContent","");
        subBusinessInfo.setExtensionInfo(extensionInfo);
        subBusinessInfos.add(subBusinessInfo);

        param.put("subBusinessInfo",subBusinessInfos);
        transParam.put("params",param);

        String s = JSONObject.toJSONString(transParam);
        System.out.println(s);
    }


}
class SubBusinessInfo{
    private String appealNo ;
    private String orderType;
    private String businessId;
    private String businessType;
    private String attachmentPath;
    private HashMap extensionInfo;

    public HashMap getExtensionInfo() {
        return extensionInfo;
    }

    public void setExtensionInfo(HashMap extensionInfo) {
        this.extensionInfo = extensionInfo;
    }

    public String getAppealNo() {
        return appealNo;
    }

    public void setAppealNo(String appealNo) {
        this.appealNo = appealNo;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }
}
 class ContactInfoBean {
    private String contactInfoType;
     private String contactInfoName;
     private String contactInfoPhone;

     public String getContactInfoType() {
         return contactInfoType;
     }

     public void setContactInfoType(String contactInfoType) {
         this.contactInfoType = contactInfoType;
     }

     public String getContactInfoName() {
         return contactInfoName;
     }

     public void setContactInfoName(String contactInfoName) {
         this.contactInfoName = contactInfoName;
     }

     public String getContactInfoPhone() {
         return contactInfoPhone;
     }

     public void setContactInfoPhone(String contactInfoPhone) {
         this.contactInfoPhone = contactInfoPhone;
     }
 }