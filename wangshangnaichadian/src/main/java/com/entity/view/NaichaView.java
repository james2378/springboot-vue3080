package com.entity.view;

import com.entity.NaichaEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 奶茶
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("naicha")
public class NaichaView extends NaichaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 奶茶类型的值
		*/
		private String naichaValue;
		/**
		* 是否上架的值
		*/
		private String shangxiaValue;



	public NaichaView() {

	}

	public NaichaView(NaichaEntity naichaEntity) {
		try {
			BeanUtils.copyProperties(this, naichaEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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












}
