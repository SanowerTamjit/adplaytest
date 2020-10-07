package com.santam.adplaytest.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {


    //"Mon, 05 Oct 2020 10:56:51 GMT"
    public Timestamp covertString2Timestamp(String timestampStr){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy hh:mm:ss z");
            Date parsedDate = dateFormat.parse(timestampStr);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

            System.out.println(parsedDate);
            return timestamp;
        } catch(Exception e) {

        }

        return null;
    }

    public static void main(String[] args) {
        Util util = new Util();
        util.covertString2Timestamp("Mon, 05 Oct 2020 10:56:51 GMT");
    }
}
