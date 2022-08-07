/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafeteria.utils;


import cafeteria.entity.Employee;

public class Auth {
    public static Employee user;

    public static void clear() {
        Auth.user = null;
    }

    public static boolean isLogin() {
        return Auth.user != null;
    }

    public static boolean isManger() {
        return "Quản lý".equalsIgnoreCase(user.getLoaiNhanVien());
    }
}
