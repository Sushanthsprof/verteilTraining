package com.example.restaurantManagement.controller;
import java.util.List;
import com.example.restaurantManagement.model.Menu;
import com.example.restaurantManagement.serviceLayer.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mc")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @PostMapping("/saveAllMenu")
    public List<Menu> saveMenus(@RequestBody List<Menu> menus){
        return menuService.saveMenus(menus);
    }
    @PostMapping("/saveMenu")
    public Menu saveMenu(@RequestBody Menu menu){
        return menuService.saveMenu(menu);
    }
    @PutMapping("/updateMenu/{id}")
    public Menu removeMenU(@PathVariable int id,@RequestBody Menu menu){
        return menuService.updateMenu(id,menu);
    }
    @GetMapping("/getFromMenu/{id}")
    public Menu getFromMenu(@PathVariable int id){
        return menuService.getFromMenu(id);
    }
}
