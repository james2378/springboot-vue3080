
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
 * 奶茶订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/naichaOrder")
public class NaichaOrderController {
    private static final Logger logger = LoggerFactory.getLogger(NaichaOrderController.class);

    @Autowired
    private NaichaOrderService naichaOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private AddressService addressService;
    @Autowired
    private NaichaService naichaService;
    @Autowired
    private YonghuService yonghuService;
@Autowired
private CartService cartService;
@Autowired
private NaichaCommentbackService naichaCommentbackService;



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
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = naichaOrderService.queryPage(params);

        //字典表数据转换
        List<NaichaOrderView> list =(List<NaichaOrderView>)page.getList();
        for(NaichaOrderView c:list){
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
        NaichaOrderEntity naichaOrder = naichaOrderService.selectById(id);
        if(naichaOrder !=null){
            //entity转view
            NaichaOrderView view = new NaichaOrderView();
            BeanUtils.copyProperties( naichaOrder , view );//把实体数据重构到view中

                //级联表
                AddressEntity address = addressService.selectById(naichaOrder.getAddressId());
                if(address != null){
                    BeanUtils.copyProperties( address , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setAddressId(address.getId());
                    view.setAddressYonghuId(address.getYonghuId());
                }
                //级联表
                NaichaEntity naicha = naichaService.selectById(naichaOrder.getNaichaId());
                if(naicha != null){
                    BeanUtils.copyProperties( naicha , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setNaichaId(naicha.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(naichaOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R save(@RequestBody NaichaOrderEntity naichaOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,naichaOrder:{}",this.getClass().getName(),naichaOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            naichaOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        naichaOrder.setInsertTime(new Date());
        naichaOrder.setCreateTime(new Date());
        naichaOrderService.insert(naichaOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody NaichaOrderEntity naichaOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,naichaOrder:{}",this.getClass().getName(),naichaOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            naichaOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<NaichaOrderEntity> queryWrapper = new EntityWrapper<NaichaOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        NaichaOrderEntity naichaOrderEntity = naichaOrderService.selectOne(queryWrapper);
        if(naichaOrderEntity==null){
            naichaOrderService.updateById(naichaOrder);//根据id更新
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
        naichaOrderService.deleteBatchIds(Arrays.asList(ids));
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
            List<NaichaOrderEntity> naichaOrderList = new ArrayList<>();//上传的东西
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
                            NaichaOrderEntity naichaOrderEntity = new NaichaOrderEntity();
//                            naichaOrderEntity.setNaichaOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            naichaOrderEntity.setAddressId(Integer.valueOf(data.get(0)));   //收货地址 要改的
//                            naichaOrderEntity.setNaichaId(Integer.valueOf(data.get(0)));   //奶茶 要改的
//                            naichaOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            naichaOrderEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //购买数量 要改的
//                            naichaOrderEntity.setNaichaOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            naichaOrderEntity.setNaichaOrderCourierName(data.get(0));                    //配送人姓名 要改的
//                            naichaOrderEntity.setNaichaOrderCourierNumber(data.get(0));                    //配送人联系方式 要改的
//                            naichaOrderEntity.setNaichaOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            naichaOrderEntity.setNaichaOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            naichaOrderEntity.setInsertTime(date);//时间
//                            naichaOrderEntity.setCreateTime(date);//时间
                            naichaOrderList.add(naichaOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("naichaOrderUuidNumber")){
                                    List<String> naichaOrderUuidNumber = seachFields.get("naichaOrderUuidNumber");
                                    naichaOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> naichaOrderUuidNumber = new ArrayList<>();
                                    naichaOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("naichaOrderUuidNumber",naichaOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<NaichaOrderEntity> naichaOrderEntities_naichaOrderUuidNumber = naichaOrderService.selectList(new EntityWrapper<NaichaOrderEntity>().in("naicha_order_uuid_number", seachFields.get("naichaOrderUuidNumber")));
                        if(naichaOrderEntities_naichaOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(NaichaOrderEntity s:naichaOrderEntities_naichaOrderUuidNumber){
                                repeatFields.add(s.getNaichaOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        naichaOrderService.insertBatch(naichaOrderList);
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
        PageUtils page = naichaOrderService.queryPage(params);

        //字典表数据转换
        List<NaichaOrderView> list =(List<NaichaOrderView>)page.getList();
        for(NaichaOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        NaichaOrderEntity naichaOrder = naichaOrderService.selectById(id);
            if(naichaOrder !=null){


                //entity转view
                NaichaOrderView view = new NaichaOrderView();
                BeanUtils.copyProperties( naichaOrder , view );//把实体数据重构到view中

                //级联表
                    AddressEntity address = addressService.selectById(naichaOrder.getAddressId());
                if(address != null){
                    BeanUtils.copyProperties( address , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setAddressId(address.getId());
                }
                //级联表
                    NaichaEntity naicha = naichaService.selectById(naichaOrder.getNaichaId());
                if(naicha != null){
                    BeanUtils.copyProperties( naicha , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setNaichaId(naicha.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(naichaOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R add(@RequestBody NaichaOrderEntity naichaOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,naichaOrder:{}",this.getClass().getName(),naichaOrder.toString());
            NaichaEntity naichaEntity = naichaService.selectById(naichaOrder.getNaichaId());
            if(naichaEntity == null){
                return R.error(511,"查不到该奶茶");
            }
            // Double naichaNewMoney = naichaEntity.getNaichaNewMoney();

            if(false){
            }
            else if((naichaEntity.getNaichaKucunNumber() -naichaOrder.getBuyNumber())<0){
                return R.error(511,"购买数量不能大于库存数量");
            }
            else if(naichaEntity.getNaichaNewMoney() == null){
                return R.error(511,"奶茶价格不能为空");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double balance = yonghuEntity.getNewMoney() - naichaEntity.getNaichaNewMoney()*naichaOrder.getBuyNumber();//余额
            buyJifen = new BigDecimal(naichaEntity.getNaichaPrice()).multiply(new BigDecimal(naichaOrder.getBuyNumber())).doubleValue();//所获积分
            if(balance<0)
                return R.error(511,"余额不够支付");
            naichaOrder.setNaichaOrderTypes(1); //设置订单状态为已支付
            naichaOrder.setNaichaOrderTruePrice(naichaEntity.getNaichaNewMoney()*naichaOrder.getBuyNumber()); //设置实付价格
            naichaOrder.setYonghuId(userId); //设置订单支付人id
            naichaOrder.setNaichaOrderUuidNumber(String.valueOf(new Date().getTime()));
            naichaOrder.setNaichaOrderPaymentTypes(1);
            naichaOrder.setInsertTime(new Date());
            naichaOrder.setCreateTime(new Date());
                naichaEntity.setNaichaKucunNumber( naichaEntity.getNaichaKucunNumber() -naichaOrder.getBuyNumber());
                naichaService.updateById(naichaEntity);
                naichaOrderService.insert(naichaOrder);//新增订单
            yonghuEntity.setNewMoney(balance);//设置金额
            yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() + buyJifen); //设置总积分
            yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() + buyJifen); //设置现积分
                if(yonghuEntity.getYonghuSumJifen()  < 10000)
                    yonghuEntity.setHuiyuandengjiTypes(1);
                else if(yonghuEntity.getYonghuSumJifen()  < 100000)
                    yonghuEntity.setHuiyuandengjiTypes(2);
                else if(yonghuEntity.getYonghuSumJifen()  < 1000000)
                    yonghuEntity.setHuiyuandengjiTypes(3);
            yonghuService.updateById(yonghuEntity);
            return R.ok();
    }
    /**
     * 添加订单
     */
    @RequestMapping("/order")
    public R add(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("order方法:,,Controller:{},,params:{}",this.getClass().getName(),params.toString());
        String naichaOrderUuidNumber = String.valueOf(new Date().getTime());

        //获取当前登录用户的id
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Integer addressId = Integer.valueOf(String.valueOf(params.get("addressId")));

        Integer naichaOrderPaymentTypes = Integer.valueOf(String.valueOf(params.get("naichaOrderPaymentTypes")));//支付类型

        String data = String.valueOf(params.get("naichas"));
        JSONArray jsonArray = JSON.parseArray(data);
        List<Map> naichas = JSON.parseObject(jsonArray.toString(), List.class);

        //获取当前登录用户的个人信息
        YonghuEntity yonghuEntity = yonghuService.selectById(userId);

        //当前订单表
        List<NaichaOrderEntity> naichaOrderList = new ArrayList<>();
        //商品表
        List<NaichaEntity> naichaList = new ArrayList<>();
        //购物车ids
        List<Integer> cartIds = new ArrayList<>();

        BigDecimal zhekou = new BigDecimal(1.0);
        // 获取折扣
        Wrapper<DictionaryEntity> dictionary = new EntityWrapper<DictionaryEntity>()
                .eq("dic_code", "huiyuandengji_types")
                .eq("dic_name", "会员等级类型")
                .eq("code_index", yonghuEntity.getHuiyuandengjiTypes())
                ;
        DictionaryEntity dictionaryEntity = dictionaryService.selectOne(dictionary);
        if(dictionaryEntity != null ){
            zhekou = BigDecimal.valueOf(Double.valueOf(dictionaryEntity.getBeizhu()));
        }

        //循环取出需要的数据
        for (Map<String, Object> map : naichas) {
           //取值
            Integer naichaId = Integer.valueOf(String.valueOf(map.get("naichaId")));//商品id
            Integer buyNumber = Integer.valueOf(String.valueOf(map.get("buyNumber")));//购买数量
            NaichaEntity naichaEntity = naichaService.selectById(naichaId);//购买的商品
            String id = String.valueOf(map.get("id"));
            if(StringUtil.isNotEmpty(id))
                cartIds.add(Integer.valueOf(id));

            //判断商品的库存是否足够
            if(naichaEntity.getNaichaKucunNumber() < buyNumber){
                //商品库存不足直接返回
                return R.error(naichaEntity.getNaichaName()+"的库存不足");
            }else{
                //商品库存充足就减库存
                naichaEntity.setNaichaKucunNumber(naichaEntity.getNaichaKucunNumber() - buyNumber);
            }

            //订单信息表增加数据
            NaichaOrderEntity naichaOrderEntity = new NaichaOrderEntity<>();

            //赋值订单信息
            naichaOrderEntity.setNaichaOrderUuidNumber(naichaOrderUuidNumber);//订单号
            naichaOrderEntity.setAddressId(addressId);//收货地址
            naichaOrderEntity.setNaichaId(naichaId);//奶茶
            naichaOrderEntity.setYonghuId(userId);//用户
            naichaOrderEntity.setBuyNumber(buyNumber);//购买数量 ？？？？？？
            naichaOrderEntity.setNaichaOrderTypes(1);//订单类型
            naichaOrderEntity.setNaichaOrderPaymentTypes(naichaOrderPaymentTypes);//支付类型
            naichaOrderEntity.setInsertTime(new Date());//订单创建时间
            naichaOrderEntity.setCreateTime(new Date());//创建时间

            //判断是什么支付方式 1代表余额 2代表积分
            if(naichaOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = new BigDecimal(naichaEntity.getNaichaNewMoney()).multiply(new BigDecimal(buyNumber)).multiply(zhekou).doubleValue();

                if(yonghuEntity.getNewMoney() - money <0 ){
                    return R.error("余额不足,请充值！！！");
                }else{
                    //计算所获得积分
                    Double buyJifen =0.0;
                        buyJifen = new BigDecimal(naichaEntity.getNaichaPrice()).multiply(new BigDecimal(buyNumber)).doubleValue();
                    yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() - money); //设置金额
                    yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() + buyJifen); //设置总积分
                    yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() + buyJifen); //设置现积分
                        if(yonghuEntity.getYonghuSumJifen()  < 10000)
                            yonghuEntity.setHuiyuandengjiTypes(1);
                        else if(yonghuEntity.getYonghuSumJifen()  < 100000)
                            yonghuEntity.setHuiyuandengjiTypes(2);
                        else if(yonghuEntity.getYonghuSumJifen()  < 1000000)
                            yonghuEntity.setHuiyuandengjiTypes(3);


                    naichaOrderEntity.setNaichaOrderTruePrice(money);

                }
            }
            naichaOrderList.add(naichaOrderEntity);
            naichaList.add(naichaEntity);

        }
        naichaOrderService.insertBatch(naichaOrderList);
        naichaService.updateBatchById(naichaList);
        yonghuService.updateById(yonghuEntity);
        if(cartIds != null && cartIds.size()>0)
            cartService.deleteBatchIds(cartIds);
        return R.ok();
    }

    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            NaichaOrderEntity naichaOrder = naichaOrderService.selectById(id);
            Integer buyNumber = naichaOrder.getBuyNumber();
            Integer naichaOrderPaymentTypes = naichaOrder.getNaichaOrderPaymentTypes();
            Integer naichaId = naichaOrder.getNaichaId();
            if(naichaId == null)
                return R.error(511,"查不到该奶茶");
            NaichaEntity naichaEntity = naichaService.selectById(naichaId);
            if(naichaEntity == null)
                return R.error(511,"查不到该奶茶");
            Double naichaNewMoney = naichaEntity.getNaichaNewMoney();
            if(naichaNewMoney == null)
                return R.error(511,"奶茶价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");

            Double zhekou = 1.0;
            // 获取折扣
            Wrapper<DictionaryEntity> dictionary = new EntityWrapper<DictionaryEntity>()
                    .eq("dic_code", "huiyuandengji_types")
                    .eq("dic_name", "会员等级类型")
                    .eq("code_index", yonghuEntity.getHuiyuandengjiTypes())
                    ;
            DictionaryEntity dictionaryEntity = dictionaryService.selectOne(dictionary);
            if(dictionaryEntity != null ){
                zhekou = Double.valueOf(dictionaryEntity.getBeizhu());
            }


            //判断是什么支付方式 1代表余额 2代表积分
            if(naichaOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = naichaEntity.getNaichaNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
                buyJifen = new BigDecimal(naichaEntity.getNaichaPrice()).multiply(new BigDecimal(buyNumber)).doubleValue();
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额
                yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() - buyJifen); //设置总积分
                if(yonghuEntity.getYonghuNewJifen() - buyJifen <0 )
                    return R.error("积分已经消费,无法退款！！！");
                yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() - buyJifen); //设置现积分

                if(yonghuEntity.getYonghuSumJifen()  < 10000)
                    yonghuEntity.setHuiyuandengjiTypes(1);
                else if(yonghuEntity.getYonghuSumJifen()  < 100000)
                    yonghuEntity.setHuiyuandengjiTypes(2);
                else if(yonghuEntity.getYonghuSumJifen()  < 1000000)
                    yonghuEntity.setHuiyuandengjiTypes(3);

            }

            naichaEntity.setNaichaKucunNumber(naichaEntity.getNaichaKucunNumber() + buyNumber);



            naichaOrder.setNaichaOrderTypes(2);//设置订单状态为退款
            naichaOrderService.updateById(naichaOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            naichaService.updateById(naichaEntity);//更新订单中奶茶的信息
            return R.ok();
    }


    /**
     * 发货
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id ,String naichaOrderCourierNumber, String naichaOrderCourierName){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        NaichaOrderEntity  naichaOrderEntity = new  NaichaOrderEntity();;
        naichaOrderEntity.setId(id);
        naichaOrderEntity.setNaichaOrderTypes(3);
        naichaOrderEntity.setNaichaOrderCourierNumber(naichaOrderCourierNumber);
        naichaOrderEntity.setNaichaOrderCourierName(naichaOrderCourierName);
        boolean b =  naichaOrderService.updateById( naichaOrderEntity);
        if(!b){
            return R.error("发货出错");
        }
        return R.ok();
    }














    /**
     * 收货
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        NaichaOrderEntity  naichaOrderEntity = new  NaichaOrderEntity();
        naichaOrderEntity.setId(id);
        naichaOrderEntity.setNaichaOrderTypes(4);
        boolean b =  naichaOrderService.updateById( naichaOrderEntity);
        if(!b){
            return R.error("收货出错");
        }
        return R.ok();
    }



    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText, Integer naichaCommentbackPingfenNumber, HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
            NaichaOrderEntity naichaOrder = naichaOrderService.selectById(id);
        if(naichaOrder == null)
            return R.error(511,"查不到该订单");
        if(naichaOrder.getNaichaOrderTypes() != 4)
            return R.error(511,"您不能评价");
        Integer naichaId = naichaOrder.getNaichaId();
        if(naichaId == null)
            return R.error(511,"查不到该奶茶");

        NaichaCommentbackEntity naichaCommentbackEntity = new NaichaCommentbackEntity();
            naichaCommentbackEntity.setId(id);
            naichaCommentbackEntity.setNaichaId(naichaId);
            naichaCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            naichaCommentbackEntity.setNaichaCommentbackText(commentbackText);
            naichaCommentbackEntity.setInsertTime(new Date());
            naichaCommentbackEntity.setReplyText(null);
            naichaCommentbackEntity.setUpdateTime(null);
            naichaCommentbackEntity.setCreateTime(new Date());
            naichaCommentbackService.insert(naichaCommentbackEntity);

            naichaOrder.setNaichaOrderTypes(5);//设置订单状态为已评价
            naichaOrderService.updateById(naichaOrder);//根据id更新
            return R.ok();
    }












}
