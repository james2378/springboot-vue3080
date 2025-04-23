package com.dao;

import com.entity.NaichaCommentbackEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.NaichaCommentbackView;

/**
 * 奶茶评价 Dao 接口
 *
 * @author 
 */
public interface NaichaCommentbackDao extends BaseMapper<NaichaCommentbackEntity> {

   List<NaichaCommentbackView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
