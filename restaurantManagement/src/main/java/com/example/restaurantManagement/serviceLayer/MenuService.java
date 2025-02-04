package com.example.restaurantManagement.serviceLayer;

import com.example.restaurantManagement.model.Menu;

import java.util.List;

public interface MenuService {
    public Menu saveMenu(Menu menu);
    public List<Menu> saveMenus(List<Menu> menu);
    public Menu updateMenu(int id,Menu menu);
    public Menu getFromMenu(int id);
}
