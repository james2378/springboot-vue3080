package com.entity.view;

import com.entity.NaichaOrderEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 奶茶订单
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("naicha_order")
public class NaichaOrderView extends NaichaOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 订单类型的值
		*/
		private String naichaOrderValue;
		/**
		* 支付类型的值
		*/
		private String naichaOrderPaymentValue;



		//级联表 address
			/**
			* 收货地址 的 创建用户
			*/
			private Integer addressYonghuId;
			/**
			* 收货人
			*/
			private String addressName;
			/**
			* 电话
			*/
			private String addressPhone;
			/**
			* 地址
			*/
			private String addressDizhi;
			/**
			* 是否默认地址
			*/
			private Integer isdefaultTypes;
				/**
				* 是否默认地址的值
				*/
				private String isdefaultValue;

		//级联表 naicha
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
				* 奶茶类型的值
				*/
				private String naichaValue;
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
				* 是否上架的值
				*/
				private String shangxiaValue;
			/**
			* 逻辑删除
			*/
			private Integer naichaDelete;

		//级联表 yonghu
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 用户手机号
			*/
			private String yonghuPhone;
			/**
			* 用户身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 用户头像
			*/
			private String yonghuPhoto;
			/**
			* 电子邮箱
			*/
			private String yonghuEmail;
			/**
			* 余额
			*/
			private Double newMoney;
			/**
			* 总积分
			*/
			private Double yonghuSumJifen;
			/**
			* 现积分
			*/
			private Double yonghuNewJifen;
			/**
			* 会员等级
			*/
			private Integer huiyuandengjiTypes;
				/**
				* 会员等级的值
				*/
				private String huiyuandengjiValue;

	public NaichaOrderView() {

	}

	public NaichaOrderView(NaichaOrderEntity naichaOrderEntity) {
		try {
			BeanUtils.copyProperties(this, naichaOrderEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 订单类型的值
			*/
			public String getNaichaOrderValue() {
				return naichaOrderValue;
			}
			/**
			* 设置： 订单类型的值
			*/
			public void setNaichaOrderValue(String naichaOrderValue) {
				this.naichaOrderValue = naichaOrderValue;
			}
			/**
			* 获取： 支付类型的值
			*/
			public String getNaichaOrderPaymentValue() {
				return naichaOrderPaymentValue;
			}
			/**
			* 设置： 支付类型的值
			*/
			public void setNaichaOrderPaymentValue(String naichaOrderPaymentValue) {
				this.naichaOrderPaymentValue = naichaOrderPaymentValue;
			}




				//级联表的get和set address

					/**
					* 获取：收货地址 的 创建用户
					*/
					public Integer getAddressYonghuId() {
						return addressYonghuId;
					}
					/**
					* 设置：收货地址 的 创建用户
					*/
					public void setAddressYonghuId(Integer addressYonghuId) {
						this.addressYonghuId = addressYonghuId;
					}


					/**
					* 获取： 收货人
					*/
					public String getAddressName() {
						return addressName;
					}
					/**
					* 设置： 收货人
					*/
					public void setAddressName(String addressName) {
						this.addressName = addressName;
					}

					/**
					* 获取： 电话
					*/
					public String getAddressPhone() {
						return addressPhone;
					}
					/**
					* 设置： 电话
					*/
					public void setAddressPhone(String addressPhone) {
						this.addressPhone = addressPhone;
					}

					/**
					* 获取： 地址
					*/
					public String getAddressDizhi() {
						return addressDizhi;
					}
					/**
					* 设置： 地址
					*/
					public void setAddressDizhi(String addressDizhi) {
						this.addressDizhi = addressDizhi;
					}

					/**
					* 获取： 是否默认地址
					*/
					public Integer getIsdefaultTypes() {
						return isdefaultTypes;
					}
					/**
					* 设置： 是否默认地址
					*/
					public void setIsdefaultTypes(Integer isdefaultTypes) {
						this.isdefaultTypes = isdefaultTypes;
					}


						/**
						* 获取： 是否默认地址的值
						*/
						public String getIsdefaultValue() {
							return isdefaultValue;
						}
						/**
						* 设置： 是否默认地址的值
						*/
						public void setIsdefaultValue(String isdefaultValue) {
							this.isdefaultValue = isdefaultValue;
						}













				//级联表的get和set naicha

					/**
					* 获取： 奶茶编号
					*/
					public String getNaichaUuidNumber() {
						return naichaUuidNumber;
					}
					/**
					* 设置： 奶茶编号
					*/
					public void setNaichaUuidNumber(String naichaUuidNumber) {
						this.naichaUuidNumber = naichaUuidNumber;
					}

					/**
					* 获取： 奶茶名称
					*/
					public String getNaichaName() {
						return naichaName;
					}
					/**
					* 设置： 奶茶名称
					*/
					public void setNaichaName(String naichaName) {
						this.naichaName = naichaName;
					}

					/**
					* 获取： 奶茶照片
					*/
					public String getNaichaPhoto() {
						return naichaPhoto;
					}
					/**
					* 设置： 奶茶照片
					*/
					public void setNaichaPhoto(String naichaPhoto) {
						this.naichaPhoto = naichaPhoto;
					}

					/**
					* 获取： 特点
					*/
					public String getNaichaTedian() {
						return naichaTedian;
					}
					/**
					* 设置： 特点
					*/
					public void setNaichaTedian(String naichaTedian) {
						this.naichaTedian = naichaTedian;
					}

					/**
					* 获取： 奶茶类型
					*/
					public Integer getNaichaTypes() {
						return naichaTypes;
					}
					/**
					* 设置： 奶茶类型
					*/
					public void setNaichaTypes(Integer naichaTypes) {
						this.naichaTypes = naichaTypes;
					}


						/**
						* 获取： 奶茶类型的值
						*/
						public String getNaichaValue() {
							return naichaValue;
						}
						/**
						* 设置： 奶茶类型的值
						*/
						public void setNaichaValue(String naichaValue) {
							this.naichaValue = naichaValue;
						}

					/**
					* 获取： 奶茶库存
					*/
					public Integer getNaichaKucunNumber() {
						return naichaKucunNumber;
					}
					/**
					* 设置： 奶茶库存
					*/
					public void setNaichaKucunNumber(Integer naichaKucunNumber) {
						this.naichaKucunNumber = naichaKucunNumber;
					}

					/**
					* 获取： 购买获得积分
					*/
					public Integer getNaichaPrice() {
						return naichaPrice;
					}
					/**
					* 设置： 购买获得积分
					*/
					public void setNaichaPrice(Integer naichaPrice) {
						this.naichaPrice = naichaPrice;
					}

					/**
					* 获取： 奶茶原价
					*/
					public Double getNaichaOldMoney() {
						return naichaOldMoney;
					}
					/**
					* 设置： 奶茶原价
					*/
					public void setNaichaOldMoney(Double naichaOldMoney) {
						this.naichaOldMoney = naichaOldMoney;
					}

					/**
					* 获取： 现价
					*/
					public Double getNaichaNewMoney() {
						return naichaNewMoney;
					}
					/**
					* 设置： 现价
					*/
					public void setNaichaNewMoney(Double naichaNewMoney) {
						this.naichaNewMoney = naichaNewMoney;
					}

					/**
					* 获取： 热度
					*/
					public Integer getNaichaClicknum() {
						return naichaClicknum;
					}
					/**
					* 设置： 热度
					*/
					public void setNaichaClicknum(Integer naichaClicknum) {
						this.naichaClicknum = naichaClicknum;
					}

					/**
					* 获取： 奶茶介绍
					*/
					public String getNaichaContent() {
						return naichaContent;
					}
					/**
					* 设置： 奶茶介绍
					*/
					public void setNaichaContent(String naichaContent) {
						this.naichaContent = naichaContent;
					}

					/**
					* 获取： 是否上架
					*/
					public Integer getShangxiaTypes() {
						return shangxiaTypes;
					}
					/**
					* 设置： 是否上架
					*/
					public void setShangxiaTypes(Integer shangxiaTypes) {
						this.shangxiaTypes = shangxiaTypes;
					}


						/**
						* 获取： 是否上架的值
						*/
						public String getShangxiaValue() {
							return shangxiaValue;
						}
						/**
						* 设置： 是否上架的值
						*/
						public void setShangxiaValue(String shangxiaValue) {
							this.shangxiaValue = shangxiaValue;
						}

					/**
					* 获取： 逻辑删除
					*/
					public Integer getNaichaDelete() {
						return naichaDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setNaichaDelete(Integer naichaDelete) {
						this.naichaDelete = naichaDelete;
					}





















				//级联表的get和set yonghu

					/**
					* 获取： 用户姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 用户姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}

					/**
					* 获取： 用户手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 用户手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}

					/**
					* 获取： 用户身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 用户身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}

					/**
					* 获取： 用户头像
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 用户头像
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}

					/**
					* 获取： 电子邮箱
					*/
					public String getYonghuEmail() {
						return yonghuEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setYonghuEmail(String yonghuEmail) {
						this.yonghuEmail = yonghuEmail;
					}

					/**
					* 获取： 余额
					*/
					public Double getNewMoney() {
						return newMoney;
					}
					/**
					* 设置： 余额
					*/
					public void setNewMoney(Double newMoney) {
						this.newMoney = newMoney;
					}

					/**
					* 获取： 总积分
					*/
					public Double getYonghuSumJifen() {
						return yonghuSumJifen;
					}
					/**
					* 设置： 总积分
					*/
					public void setYonghuSumJifen(Double yonghuSumJifen) {
						this.yonghuSumJifen = yonghuSumJifen;
					}

					/**
					* 获取： 现积分
					*/
					public Double getYonghuNewJifen() {
						return yonghuNewJifen;
					}
					/**
					* 设置： 现积分
					*/
					public void setYonghuNewJifen(Double yonghuNewJifen) {
						this.yonghuNewJifen = yonghuNewJifen;
					}

					/**
					* 获取： 会员等级
					*/
					public Integer getHuiyuandengjiTypes() {
						return huiyuandengjiTypes;
					}
					/**
					* 设置： 会员等级
					*/
					public void setHuiyuandengjiTypes(Integer huiyuandengjiTypes) {
						this.huiyuandengjiTypes = huiyuandengjiTypes;
					}


						/**
						* 获取： 会员等级的值
						*/
						public String getHuiyuandengjiValue() {
							return huiyuandengjiValue;
						}
						/**
						* 设置： 会员等级的值
						*/
						public void setHuiyuandengjiValue(String huiyuandengjiValue) {
							this.huiyuandengjiValue = huiyuandengjiValue;
						}




}
