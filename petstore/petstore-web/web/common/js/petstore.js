﻿const PetStore = {
    Host: '',
}

//在文件上传时用到
PetStore.StoreService = PetStore.Host + '/service/store_service';

const UserService = Lealone.getService("user_service");
const StoreService = Lealone.getService("store_service");
const ViewCartService = Lealone.getService("view_cart_service");

const mount = function(app, appName) {
    // 混入全局的logout方法
    app.mixin({
        methods: {
            logout() {
                localStorage.removeItem("currentUser");
                axios.get("/user/logout")
                .then(response => {
                    location.href = "/";
                });
            }
        }
    });
    Lealone.loadServices(services => {
        // console.log(services);
        app.mount(appName)
    });
}