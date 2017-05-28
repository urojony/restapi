package pl.amu.service.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by win10 on 20.05.2017.
 */
public class MyDateparser {
    static private List<SimpleDateFormat> sdfs=new ArrayList<>();
    public MyDateparser(){
        sdfs.add(new SimpleDateFormat("yyyy-MM-dd"));
        sdfs.add(new SimpleDateFormat("dd-MM-yyyy"));
        sdfs.add(new SimpleDateFormat("yyyy.MM.dd"));
        sdfs.add(new SimpleDateFormat("dd.MM.yyyy"));
    }
    public Date parse(String dt)
    {
        Date resultdate=null;
        for(SimpleDateFormat sdf:sdfs){
            try{
                resultdate=sdf.parse(dt);
                return resultdate;
            }
            catch(Exception e) {
            }
        }
        return null;
    }
}
