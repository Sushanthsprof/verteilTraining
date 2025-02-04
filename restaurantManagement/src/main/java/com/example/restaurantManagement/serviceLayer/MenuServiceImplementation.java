package com.example.restaurantManagement.serviceLayer;

import com.example.restaurantManagement.model.Menu;
import com.example.restaurantManagement.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImplementation implements MenuService{
    @Autowired
    private MenuRepository menuRepository;
    @Override
    public Menu saveMenu(Menu menu){
        return menuRepository.save(menu);
    }
    @Override
    public List<Menu> saveMenus(List<Menu> menu){
        return menuRepository.saveAll(menu);
    }
    @Override
    public Menu updateMenu(int id,Menu menu){
        Optional<Menu> existingMenu=menuRepository.findById(id);
        if(existingMenu.isPresent()){
            existingMenu.get().setName(menu.getName());
            existingMenu.get().setPrice(menu.getPrice());
            menuRepository.save(existingMenu.get());
            return existingMenu.get();
        }
        System.out.println("NO MENU ITEM FOUND");
        return null;
    }
    @Override
    public Menu getFromMenu(int id){
        Optional<Menu> menu=menuRepository.findById(id);
        if(menu.isPresent()){
            return menu.get();
        }
        System.out.println("NO MENU WITH ID : "+ id +" has been found");
        return null;
    }
}
