package com.entity.model;

import com.entity.NaichaEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 奶茶
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class NaichaModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 奶茶编号
     */
    private String naichaUuidNumber;


    /**
     * 奶茶名称
     */
    private String naichaName;


    /**
     * 奶茶照片
     */
    private String naichaPhoto;


    /**
     * 特点
     */
    private String naichaTedian;


    /**
     * 奶茶类型
     */
    private Integer naichaTypes;


    /**
     * 奶茶库存
     */
    private Integer naichaKucunNumber;


    /**
     * 购买获得积分
     */
    private Integer naichaPrice;


    /**
     * 奶茶原价
     */
    private Double naichaOldMoney;


    /**
     * 现价
     */
    private Double naichaNewMoney;


    /**
     * 热度
     */
    private Integer naichaClicknum;


    /**
     * 奶茶介绍
     */
    private String naichaContent;


    /**
     * 是否上架
     */
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    private Integer naichaDelete;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：奶茶编号
	 */
    public String getNaichaUuidNumber() {
        return naichaUuidNumber;
    }


    /**
	 * 设置：奶茶编号
	 */
    public void setNaichaUuidNumber(String naichaUuidNumber) {
        this.naichaUuidNumber = naichaUuidNumber;
    }
    /**
	 * 获取：奶茶名称
	 */
    public String getNaichaName() {
        return naichaName;
    }


    /**
	 * 设置：奶茶名称
	 */
    public void setNaichaName(String naichaName) {
        this.naichaName = naichaName;
    }
    /**
	 * 获取：奶茶照片
	 */
    public String getNaichaPhoto() {
        return naichaPhoto;
    }


    /**
	 * 设置：奶茶照片
	 */
    public void setNaichaPhoto(String naichaPhoto) {
        this.naichaPhoto = naichaPhoto;
    }
    /**
	 * 获取：特点
	 */
    public String getNaichaTedian() {
        return naichaTedian;
    }


    /**
	 * 设置：特点
	 */
    public void setNaichaTedian(String naichaTedian) {
        this.naichaTedian = naichaTedian;
    }
    /**
	 * 获取：奶茶类型
	 */
    public Integer getNaichaTypes() {
        return naichaTypes;
    }


    /**
	 * 设置：奶茶类型
	 */
    public void setNaichaTypes(Integer naichaTypes) {
        this.naichaTypes = naichaTypes;
    }
    /**
	 * 获取：奶茶库存
	 */
    public Integer getNaichaKucunNumber() {
        return naichaKucunNumber;
    }


    /**
	 * 设置：奶茶库存
	 */
    public void setNaichaKucunNumber(Integer naichaKucunNumber) {
        this.naichaKucunNumber = naichaKucunNumber;
    }
    /**
	 * 获取：购买获得积分
	 */
    public Integer getNaichaPrice() {
        return naichaPrice;
    }


    /**
	 * 设置：购买获得积分
	 */
    public void setNaichaPrice(Integer naichaPrice) {
        this.naichaPrice = naichaPrice;
    }
    /**
	 * 获取：奶茶原价
	 */
    public Double getNaichaOldMoney() {
        return naichaOldMoney;
    }


    /**
	 * 设置：奶茶原价
	 */
    public void setNaichaOldMoney(Double naichaOldMoney) {
        this.naichaOldMoney = naichaOldMoney;
    }
    /**
	 * 获取：现价
	 */
    public Double getNaichaNewMoney() {
        return naichaNewMoney;
    }


    /**
	 * 设置：现价
	 */
    public void setNaichaNewMoney(Double naichaNewMoney) {
        this.naichaNewMoney = naichaNewMoney;
    }
    /**
	 * 获取：热度
	 */
    public Integer getNaichaClicknum() {
        return naichaClicknum;
    }


    /**
	 * 设置：热度
	 */
    public void setNaichaClicknum(Integer naichaClicknum) {
        this.naichaClicknum = naichaClicknum;
    }
    /**
	 * 获取：奶茶介绍
	 */
    public String getNaichaContent() {
        return naichaContent;
    }


    /**
	 * 设置：奶茶介绍
	 */
    public void setNaichaContent(String naichaContent) {
        this.naichaContent = naichaContent;
    }
    /**
	 * 获取：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 设置：是否上架
	 */
    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getNaichaDelete() {
        return naichaDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setNaichaDelete(Integer naichaDelete) {
        this.naichaDelete = naichaDelete;
    }
    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
