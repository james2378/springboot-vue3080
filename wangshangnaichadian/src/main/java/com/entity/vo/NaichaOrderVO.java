package com.entity.vo;

import com.entity.NaichaOrderEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 奶茶订单
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("naicha_order")
public class NaichaOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 订单号
     */

    @TableField(value = "naicha_order_uuid_number")
    private String naichaOrderUuidNumber;


    /**
     * 收货地址
     */

    @TableField(value = "address_id")
    private Integer addressId;


    /**
     * 奶茶
     */

    @TableField(value = "naicha_id")
    private Integer naichaId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 购买数量
     */

    @TableField(value = "buy_number")
    private Integer buyNumber;


    /**
     * 实付价格
     */

    @TableField(value = "naicha_order_true_price")
    private Double naichaOrderTruePrice;


    /**
     * 配送人姓名
     */

    @TableField(value = "naicha_order_courier_name")
    private String naichaOrderCourierName;


    /**
     * 配送人联系方式
     */

    @TableField(value = "naicha_order_courier_number")
    private String naichaOrderCourierNumber;


    /**
     * 订单类型
     */

    @TableField(value = "naicha_order_types")
    private Integer naichaOrderTypes;


    /**
     * 支付类型
     */

    @TableField(value = "naicha_order_payment_types")
    private Integer naichaOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：订单号
	 */
    public String getNaichaOrderUuidNumber() {
        return naichaOrderUuidNumber;
    }


    /**
	 * 获取：订单号
	 */

    public void setNaichaOrderUuidNumber(String naichaOrderUuidNumber) {
        this.naichaOrderUuidNumber = naichaOrderUuidNumber;
    }
    /**
	 * 设置：收货地址
	 */
    public Integer getAddressId() {
        return addressId;
    }


    /**
	 * 获取：收货地址
	 */

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
    /**
	 * 设置：奶茶
	 */
    public Integer getNaichaId() {
        return naichaId;
    }


    /**
	 * 获取：奶茶
	 */

    public void setNaichaId(Integer naichaId) {
        this.naichaId = naichaId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：购买数量
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 获取：购买数量
	 */

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 设置：实付价格
	 */
    public Double getNaichaOrderTruePrice() {
        return naichaOrderTruePrice;
    }


    /**
	 * 获取：实付价格
	 */

    public void setNaichaOrderTruePrice(Double naichaOrderTruePrice) {
        this.naichaOrderTruePrice = naichaOrderTruePrice;
    }
    /**
	 * 设置：配送人姓名
	 */
    public String getNaichaOrderCourierName() {
        return naichaOrderCourierName;
    }


    /**
	 * 获取：配送人姓名
	 */

    public void setNaichaOrderCourierName(String naichaOrderCourierName) {
        this.naichaOrderCourierName = naichaOrderCourierName;
    }
    /**
	 * 设置：配送人联系方式
	 */
    public String getNaichaOrderCourierNumber() {
        return naichaOrderCourierNumber;
    }


    /**
	 * 获取：配送人联系方式
	 */

    public void setNaichaOrderCourierNumber(String naichaOrderCourierNumber) {
        this.naichaOrderCourierNumber = naichaOrderCourierNumber;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getNaichaOrderTypes() {
        return naichaOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setNaichaOrderTypes(Integer naichaOrderTypes) {
        this.naichaOrderTypes = naichaOrderTypes;
    }
    /**
	 * 设置：支付类型
	 */
    public Integer getNaichaOrderPaymentTypes() {
        return naichaOrderPaymentTypes;
    }


    /**
	 * 获取：支付类型
	 */

    public void setNaichaOrderPaymentTypes(Integer naichaOrderPaymentTypes) {
        this.naichaOrderPaymentTypes = naichaOrderPaymentTypes;
    }
    /**
	 * 设置：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
