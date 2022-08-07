/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafeteria.utils;

import cafeteria.exception.FormatVietNamException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LocalVietNam {
    
    private static SimpleDateFormat vietNamFormatDate = new SimpleDateFormat();

    /**
     *
     * @return format Date in VietNam
     * @Example today is November 22, 2020. VN'format is 22-11-2020
     */
    public static String getDate(Date date) {
        vietNamFormatDate.setLenient(false);
        vietNamFormatDate.applyPattern("dd-MM-yyyy");
        return vietNamFormatDate.format(date);
    }

    /**
     *
     * @param money is String
     * @return format currency in VietNam
     * @throws FormatVietNamException if integer is not number or is lower than
     * Zero
     * @Excample input: 1000000 output: 1.000.000
     *
     */
    public static String getCurrency(String money) throws FormatVietNamException {
        if (!isNunmber(money)) {
            throw new FormatVietNamException("Tiền phải là số");
        }
        
        StringBuilder duplicateMoney = new StringBuilder(money);
        duplicateMoney.reverse();
        String newMoney = "";
        for (int i = 0; i < duplicateMoney.length(); i++) {
            
            newMoney += duplicateMoney.charAt(i);
            
            if ((i + 1) % 3 == 0 && i < duplicateMoney.length() - 1) {
                newMoney += ".";
            }
        }

        return new StringBuilder(newMoney).reverse()+".000đ";
    }

    /**
     *
     * @param money is Object
     * @return format currency in VietNam
     * @throws FormatVietNamException if money is not a number or is lower than Zero
     * @Excample input: 1000000 output: 1.000.000
     *
     */
    public static String getCurrency(Object money) throws FormatVietNamException {
        return getCurrency(money.toString());
    }

    /**
     *
     * @param integer can be money, minute ...
     * @return true if integer is lower than Zero and false is greater than Zero
     * @Example input: 1 | -1 output: true | false
     */
    private static boolean isLowerZero(String integer) {
        return Integer.parseInt(integer) < 0;
    }

    /**
     *
     * @param number can be money, minute
     * @return true if number is a number and false if number is a String
     * @Example input: 60 | sixty output: true | false
     */
    private static boolean isNunmber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     *
     * @param minute: minute to convert
     * @return format time in VietNam
     * @throws FormatVietNamException if int is lower than 0
     *
     * @Example input: minute = 700 output: 11 hours 40 minutes
     *
     */
    public static String getTime(int minute) throws FormatVietNamException {
        if (minute < 0) {
            throw new FormatVietNamException("Phút không thể nhỏ hơn 0");
        }
        int hour = 0;
        while (minute >= 60) {
            hour++;
            minute -= 60;
        }
        return String.format("%02d%s%02d%s", hour, "tiếng ", minute, "phút");
    }
    
    public static String getTime(Object minute) throws FormatVietNamException{
        String phut = String.valueOf(minute);
        if (!isNunmber(phut)) {
            throw new FormatVietNamException("Phút phải là số");
        }
        return getTime(Integer.parseInt(phut));
    }
}