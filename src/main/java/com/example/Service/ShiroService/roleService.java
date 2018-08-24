package com.example.Service.ShiroService;

import com.example.model.role;

import java.util.List;

public interface roleService {

    public List<role> allRole();

    public String getRoleName(String userName);
}
