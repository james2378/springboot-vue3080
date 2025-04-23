
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 奶茶
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/naicha")
public class NaichaController {
    private static final Logger logger = LoggerFactory.getLogger(NaichaController.class);

    @Autowired
    private NaichaService naichaService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private YonghuService yonghuService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        params.put("naichaDeleteStart",1);params.put("naichaDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = naichaService.queryPage(params);

        //字典表数据转换
        List<NaichaView> list =(List<NaichaView>)page.getList();
        for(NaichaView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        NaichaEntity naicha = naichaService.selectById(id);
        if(naicha !=null){
            //entity转view
            NaichaView view = new NaichaView();
            BeanUtils.copyProperties( naicha , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody NaichaEntity naicha, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,naicha:{}",this.getClass().getName(),naicha.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<NaichaEntity> queryWrapper = new EntityWrapper<NaichaEntity>()
            .eq("naicha_uuid_number", naicha.getNaichaUuidNumber())
            .eq("naicha_name", naicha.getNaichaName())
            .eq("naicha_tedian", naicha.getNaichaTedian())
            .eq("naicha_types", naicha.getNaichaTypes())
            .eq("naicha_kucun_number", naicha.getNaichaKucunNumber())
            .eq("naicha_price", naicha.getNaichaPrice())
            .eq("naicha_clicknum", naicha.getNaichaClicknum())
            .eq("shangxia_types", naicha.getShangxiaTypes())
            .eq("naicha_delete", naicha.getNaichaDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        NaichaEntity naichaEntity = naichaService.selectOne(queryWrapper);
        if(naichaEntity==null){
            naicha.setNaichaClicknum(1);
            naicha.setShangxiaTypes(1);
            naicha.setNaichaDelete(1);
            naicha.setCreateTime(new Date());
            naichaService.insert(naicha);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody NaichaEntity naicha, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,naicha:{}",this.getClass().getName(),naicha.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<NaichaEntity> queryWrapper = new EntityWrapper<NaichaEntity>()
            .notIn("id",naicha.getId())
            .andNew()
            .eq("naicha_uuid_number", naicha.getNaichaUuidNumber())
            .eq("naicha_name", naicha.getNaichaName())
            .eq("naicha_tedian", naicha.getNaichaTedian())
            .eq("naicha_types", naicha.getNaichaTypes())
            .eq("naicha_kucun_number", naicha.getNaichaKucunNumber())
            .eq("naicha_price", naicha.getNaichaPrice())
            .eq("naicha_clicknum", naicha.getNaichaClicknum())
            .eq("shangxia_types", naicha.getShangxiaTypes())
            .eq("naicha_delete", naicha.getNaichaDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        NaichaEntity naichaEntity = naichaService.selectOne(queryWrapper);
        if("".equals(naicha.getNaichaPhoto()) || "null".equals(naicha.getNaichaPhoto())){
                naicha.setNaichaPhoto(null);
        }
        if(naichaEntity==null){
            naichaService.updateById(naicha);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        ArrayList<NaichaEntity> list = new ArrayList<>();
        for(Integer id:ids){
            NaichaEntity naichaEntity = new NaichaEntity();
            naichaEntity.setId(id);
            naichaEntity.setNaichaDelete(2);
            list.add(naichaEntity);
        }
        if(list != null && list.size() >0){
            naichaService.updateBatchById(list);
        }
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<NaichaEntity> naichaList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("../../upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            NaichaEntity naichaEntity = new NaichaEntity();
//                            naichaEntity.setNaichaUuidNumber(data.get(0));                    //奶茶编号 要改的
//                            naichaEntity.setNaichaName(data.get(0));                    //奶茶名称 要改的
//                            naichaEntity.setNaichaPhoto("");//详情和图片
//                            naichaEntity.setNaichaTedian(data.get(0));                    //特点 要改的
//                            naichaEntity.setNaichaTypes(Integer.valueOf(data.get(0)));   //奶茶类型 要改的
//                            naichaEntity.setNaichaKucunNumber(Integer.valueOf(data.get(0)));   //奶茶库存 要改的
//                            naichaEntity.setNaichaPrice(Integer.valueOf(data.get(0)));   //购买获得积分 要改的
//                            naichaEntity.setNaichaOldMoney(data.get(0));                    //奶茶原价 要改的
//                            naichaEntity.setNaichaNewMoney(data.get(0));                    //现价 要改的
//                            naichaEntity.setNaichaClicknum(Integer.valueOf(data.get(0)));   //热度 要改的
//                            naichaEntity.setNaichaContent("");//详情和图片
//                            naichaEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            naichaEntity.setNaichaDelete(1);//逻辑删除字段
//                            naichaEntity.setCreateTime(date);//时间
                            naichaList.add(naichaEntity);


                            //把要查询是否重复的字段放入map中
                                //奶茶编号
                                if(seachFields.containsKey("naichaUuidNumber")){
                                    List<String> naichaUuidNumber = seachFields.get("naichaUuidNumber");
                                    naichaUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> naichaUuidNumber = new ArrayList<>();
                                    naichaUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("naichaUuidNumber",naichaUuidNumber);
                                }
                        }

                        //查询是否重复
                         //奶茶编号
                        List<NaichaEntity> naichaEntities_naichaUuidNumber = naichaService.selectList(new EntityWrapper<NaichaEntity>().in("naicha_uuid_number", seachFields.get("naichaUuidNumber")).eq("naicha_delete", 1));
                        if(naichaEntities_naichaUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(NaichaEntity s:naichaEntities_naichaUuidNumber){
                                repeatFields.add(s.getNaichaUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [奶茶编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        naichaService.insertBatch(naichaList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = naichaService.queryPage(params);

        //字典表数据转换
        List<NaichaView> list =(List<NaichaView>)page.getList();
        for(NaichaView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        NaichaEntity naicha = naichaService.selectById(id);
            if(naicha !=null){

                //点击数量加1
                naicha.setNaichaClicknum(naicha.getNaichaClicknum()+1);
                naichaService.updateById(naicha);

                //entity转view
                NaichaView view = new NaichaView();
                BeanUtils.copyProperties( naicha , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody NaichaEntity naicha, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,naicha:{}",this.getClass().getName(),naicha.toString());
        Wrapper<NaichaEntity> queryWrapper = new EntityWrapper<NaichaEntity>()
            .eq("naicha_uuid_number", naicha.getNaichaUuidNumber())
            .eq("naicha_name", naicha.getNaichaName())
            .eq("naicha_tedian", naicha.getNaichaTedian())
            .eq("naicha_types", naicha.getNaichaTypes())
            .eq("naicha_kucun_number", naicha.getNaichaKucunNumber())
            .eq("naicha_price", naicha.getNaichaPrice())
            .eq("naicha_clicknum", naicha.getNaichaClicknum())
            .eq("shangxia_types", naicha.getShangxiaTypes())
            .eq("naicha_delete", naicha.getNaichaDelete())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        NaichaEntity naichaEntity = naichaService.selectOne(queryWrapper);
        if(naichaEntity==null){
            naicha.setNaichaDelete(1);
            naicha.setCreateTime(new Date());
        naichaService.insert(naicha);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}
