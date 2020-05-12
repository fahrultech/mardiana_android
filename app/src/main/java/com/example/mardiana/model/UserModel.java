package com.example.mardiana.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserModel {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("email")
    @Expose
    private String email;

    public UserModel(String id){
        this.id = id;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public class UserDataModel extends MessageModel {
        private List<UserModel> results;

        public List<UserModel> getResults(){
            return results;
        }

    }
}

