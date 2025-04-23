package com.dao;

import com.entity.NaichaEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.NaichaView;

/**
 * 奶茶 Dao 接口
 *
 * @author 
 */
public interface NaichaDao extends BaseMapper<NaichaEntity> {

   List<NaichaView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
