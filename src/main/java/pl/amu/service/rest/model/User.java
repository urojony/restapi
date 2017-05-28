package pl.amu.service.rest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@ApiModel
public class User{

    @ApiModelProperty("Username")
    @NotNull
 	private String name;

    @ApiModelProperty("Unique login")
    @NotNull
    private String login;


    private String email;
    public Set<String> observedcats=new HashSet<>();
    private int version=1;
    public User() {
        //
    }

    public User(String name, String login, String email) {
        this.name = name;
        this.login = login;
        this.email = email;
    }
    public void updateUser(User user) {
        name=user.getName();
        email=user.getEmail();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getObservedcats() {
        return observedcats;
    }

    public void setObservedcats(Set<String> cats) {this.observedcats=cats;}
    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!login.equals(user.login)) return false;

        return true;
    }*/


    @Override
    public int hashCode() {
        return login.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void nextversion(){
        version+=1;
    }
    public int showversion(){
        return version;
    }
}
