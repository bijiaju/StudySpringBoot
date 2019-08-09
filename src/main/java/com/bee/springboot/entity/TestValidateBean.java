package com.bee.springboot.entity;

import com.bee.springboot.entity.constrains.MiniValidation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * 测试校验参数对错的bean
 * @author bee
 * created at 2019/08/02
 */
@ApiModel(value = "测试校验参数类",description = "我是描述")
public class TestValidateBean {

    /**
     * 处理时间的时间格式
     */
    private static final DateTimeFormatter DEAL_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").withLocale(Locale.CHINA).withZone(ZoneId.systemDefault());

    @ApiModelProperty(value = "省份编码")
    @Pattern(regexp = "\\d{3}", message = "省份编码为三位数字", groups = MiniValidation.class)
    private String province;

    @ApiModelProperty(value = "xxx端口号")
    @NotEmpty(message = "xxxxx不能为空", groups = MiniValidation.class)
    private String servicecode;

    @ApiModelProperty(value = "xxx生成时间")
    @Pattern(regexp = "\\d{14}", message = "时间格式为yyyyMMddHHmmss", groups = MiniValidation.class)
    private String dealtime;

    @ApiModelProperty(value = "手机号")
    @Pattern(regexp = "1\\d{10}", message = "手机号是1开头的11位数字", groups = MiniValidation.class)
    private String phone;

    @ApiModelProperty(value = "xxxx原文")
    @NotEmpty(message = "xxx原文不能为空", groups = MiniValidation.class)
    private String content;

    @ApiModelProperty(value = "数据类型，sms：xxxx类xx原文其他类型根据需要添加")
    @Pattern(regexp = "sms", message = "数据类型不正确", groups = MiniValidation.class)
    private String datatype;

    public TestValidateBean() {
    }

    public TestValidateBean(String province, String dealtime, String phone, String content) {
        this.province = province;
        this.dealtime = dealtime;
        this.phone = phone;
        this.content = content;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getServicecode() {
        return servicecode;
    }

    public void setServicecode(String servicecode) {
        this.servicecode = servicecode;
    }

    public String getDealtime() {
        return dealtime;
    }

    public void setDealtime(String dealtime) {
        this.dealtime = dealtime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    @Override
    public String toString() {
        return "SmPushParam{" +
                "province='" + province + '\'' +
                ", servicecode='" + servicecode + '\'' +
                ", dealtime='" + dealtime + '\'' +
                ", phone='" + phone + '\'' +
                ", content='" + content + '\'' +
                ", datatype='" + datatype + '\'' +
                '}';
    }

    public static String formatDealTime(Date dealTime) {
        return DEAL_TIME_FORMATTER.format(dealTime.toInstant());
    }

    public static Date parseDealTime(String dealtime) {
        return Date.from(LocalDateTime.parse(dealtime, DEAL_TIME_FORMATTER).atZone(ZoneId.systemDefault()).toInstant());
    }

}
