package com.wni.demo;

import org.openstack4j.api.OSClient;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.identity.v3.User;
import org.openstack4j.openstack.OSFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;
/**
 * Created by niwei on 2019/5/13.
 */
public class DemoTest {

    // 用户名
    public static String userName = "admin";

    // 密码
    public static String password = "b3d5b75532a84b90";

    // openstack 对外地址
    public static String endpointUrl = "http://openstack.feikongbao.cn:5000/v3";

    // 域
    public static String domainName = "default";

    // 租户
    public static String projectName = "admin";



    public static void main(String[] args) {

        // 连接
        OSClient.OSClientV3 os = conOpenStack();

        System.out.println("token:" + os.getToken());

        // 用户列表
        List<? extends User> users = os.identity().users().list();
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
