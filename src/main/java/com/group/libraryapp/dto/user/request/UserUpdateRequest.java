package com.group.libraryapp.dto.user.request;

public class UserUpdateRequest {
    public Long id;
    public String name;

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }
}
