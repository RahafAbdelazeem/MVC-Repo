package com.admin.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
     private static SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd/MM/yyy");
     public static String dateToString (Date date){
         return simpleDateFormat.format(date);
     }
     public static Date stringToDate(String stringDate)throws ParseException
     {
         return simpleDateFormat.parse(stringDate);
     }
      public  static Date formateDate(Date date)throws ParseException
      {
          String strDate=simpleDateFormat.format(date);
          return stringToDate(strDate);
      }

}
