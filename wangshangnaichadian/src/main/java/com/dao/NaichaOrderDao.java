package com.dao;

import com.entity.NaichaOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.NaichaOrderView;

/**
 * 奶茶订单 Dao 接口
 *
 * @author 
 */
public interface NaichaOrderDao extends BaseMapper<NaichaOrderEntity> {

   List<NaichaOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
