package com.wni.demo;

import org.openstack4j.api.OSClient;
import org.openstack4j.api.identity.v3.UserService;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.identity.v3.User;
import org.openstack4j.openstack.OSFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;
/**
 * Created by niwei on 2019/7/22.
 */
public class SwiftDemoTest {
    // 用户名
    public static String userName = "admin";

    // 密码
    public static String password = "ADMIN_PASS";

    // openstack 对外地址
    //public static String endpointUrl = "http://openstack.feikongbao.cn:5000/v3";
    public static String endpointUrl = "http://fat.feikongbao.cn:5000/v3";

    // 域
    //public static String domainName = "default";
    public static String domainName = "default";

    // 租户
    public static String projectName = "admin";



    public static void main(String[] args) {

        // 连接
        OSClient.OSClientV3 os = conOpenStack();

        System.out.println("token:" + os.getToken());

        // 用户列表
        //List<? extends User> users = os.identity().users().list();
        org.openstack4j.api.identity.v3.IdentityService  mycli = os.identity();

        UserService mysev = mycli.users();

        List<? extends User> users = mysev.list();


        if (!CollectionUtils.isEmpty(users)) {
            for (User user : users) {
                System.out.println(user.getName());
            }
        }


    }

    // 创建连接
    public static OSClient.OSClientV3 conOpenStack() {

        OSClient.OSClientV3 os = OSFactory.builderV3()
            .endpoint(endpointUrl)
            .credentials(userName, password, Identifier.byId(domainName))
            .scopeToProject(Identifier.byName(projectName), Identifier.byId(domainName))
            .authenticate();


        return os;

    }

}
