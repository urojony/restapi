package pl.amu.service.rest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import pl.amu.service.rest.MyDateparser;

/**
 * Created by win10 on 13.05.2017.
 */
@ApiModel
public class Cat {
    @ApiModelProperty("Catname")
    @NotNull
    private String name;
    @ApiModelProperty("Catid")
    @NotNull
    private String id;
    @ApiModelProperty("supported formats: yyyy-MM-dd, dd-MM-yyyy, yyyy.MM.dd, dd.MM.yyyy,")
    private Date dateofbirth;
    public Set<String> hoomans=new HashSet<>();
    private int version=1;
    public Cat(String Id,String Name,Date Dateofbirth)
    {
        this.id=Id;
        this.name=Name;
        dateofbirth=Dateofbirth;
    }
    public Cat(String Id, String Name, String Dateofbirth)
    {
        id=Id;
        name=Name;
        MyDateparser sdf = new MyDateparser();
        dateofbirth=sdf.parse(Dateofbirth);
    }
    public Cat() {}
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getDateofbirth(){
        return new SimpleDateFormat("yyyy-MM-dd").format(dateofbirth);
    }
    public Date DDateofbirth() {return dateofbirth;}
    public void setId(String Id){
        id=Id;
    }
    public void setName(String Name){
        name=Name;
    }
    public void setDateofbirth(Date Dateofbirth,int i){
        dateofbirth=Dateofbirth;
    }
    public void setDateofbirth(String Dateofbirth) {
        MyDateparser DP=new MyDateparser();
        dateofbirth=DP.parse(Dateofbirth);
    }
    public void updateCat(Cat cat){
        name=cat.getName();
        dateofbirth=cat.DDateofbirth();
    }
    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cat cat = (Cat) o;

        if (!id.equals(cat.id)) return false;

        return true;
    }*/

    @Override
    public int hashCode() {
        return id.hashCode();
    }
    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
     //           ", dateofbirth='" + dateofbirth + '\'' +
                '}';
    }

    public void nextversion(){
        version+=1;
    }
    public int showversion(){
        return version;
    }
}
