const base = {
    get() {
        return {
            url : "http://localhost:8080/wangshangnaichadian/",
            name: "wangshangnaichadian",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/wangshangnaichadian/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "网上奶茶店系统"
        } 
    }
}
export default base
