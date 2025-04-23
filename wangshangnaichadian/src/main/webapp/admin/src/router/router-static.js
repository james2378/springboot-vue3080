import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'

     import users from '@/views/modules/users/list'
    import address from '@/views/modules/address/list'
    import cart from '@/views/modules/cart/list'
    import dictionary from '@/views/modules/dictionary/list'
    import naicha from '@/views/modules/naicha/list'
    import naichaCollection from '@/views/modules/naichaCollection/list'
    import naichaCommentback from '@/views/modules/naichaCommentback/list'
    import naichaOrder from '@/views/modules/naichaOrder/list'
    import singleSeach from '@/views/modules/singleSeach/list'
    import yonghu from '@/views/modules/yonghu/list'
    import config from '@/views/modules/config/list'
    import dictionaryHuiyuandengji from '@/views/modules/dictionaryHuiyuandengji/list'
    import dictionaryIsdefault from '@/views/modules/dictionaryIsdefault/list'
    import dictionaryNaicha from '@/views/modules/dictionaryNaicha/list'
    import dictionaryNaichaCollection from '@/views/modules/dictionaryNaichaCollection/list'
    import dictionaryNaichaOrder from '@/views/modules/dictionaryNaichaOrder/list'
    import dictionaryNaichaOrderPayment from '@/views/modules/dictionaryNaichaOrderPayment/list'
    import dictionarySex from '@/views/modules/dictionarySex/list'
    import dictionaryShangxia from '@/views/modules/dictionaryShangxia/list'
    import dictionarySingleSeach from '@/views/modules/dictionarySingleSeach/list'





//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    } ,{
        path: '/users',
        name: '管理信息',
        component: users
      }
    ,{
        path: '/dictionaryHuiyuandengji',
        name: '会员等级类型',
        component: dictionaryHuiyuandengji
    }
    ,{
        path: '/dictionaryIsdefault',
        name: '是否默认地址',
        component: dictionaryIsdefault
    }
    ,{
        path: '/dictionaryNaicha',
        name: '奶茶类型',
        component: dictionaryNaicha
    }
    ,{
        path: '/dictionaryNaichaCollection',
        name: '收藏表类型',
        component: dictionaryNaichaCollection
    }
    ,{
        path: '/dictionaryNaichaOrder',
        name: '订单类型',
        component: dictionaryNaichaOrder
    }
    ,{
        path: '/dictionaryNaichaOrderPayment',
        name: '订单支付类型',
        component: dictionaryNaichaOrderPayment
    }
    ,{
        path: '/dictionarySex',
        name: '性别类型',
        component: dictionarySex
    }
    ,{
        path: '/dictionaryShangxia',
        name: '上下架',
        component: dictionaryShangxia
    }
    ,{
        path: '/dictionarySingleSeach',
        name: '单页数据类型',
        component: dictionarySingleSeach
    }
    ,{
        path: '/config',
        name: '轮播图',
        component: config
    }


    ,{
        path: '/address',
        name: '收货地址',
        component: address
      }
    ,{
        path: '/cart',
        name: '购物车',
        component: cart
      }
    ,{
        path: '/dictionary',
        name: '字典',
        component: dictionary
      }
    ,{
        path: '/naicha',
        name: '奶茶',
        component: naicha
      }
    ,{
        path: '/naichaCollection',
        name: '奶茶收藏',
        component: naichaCollection
      }
    ,{
        path: '/naichaCommentback',
        name: '奶茶评价',
        component: naichaCommentback
      }
    ,{
        path: '/naichaOrder',
        name: '奶茶订单',
        component: naichaOrder
      }
    ,{
        path: '/singleSeach',
        name: '单页数据',
        component: singleSeach
      }
    ,{
        path: '/yonghu',
        name: '用户',
        component: yonghu
      }


    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
