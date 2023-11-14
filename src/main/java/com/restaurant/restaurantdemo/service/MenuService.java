package com.restaurant.restaurantdemo.service;


import com.restaurant.restaurantdemo.model.Menu;
import com.restaurant.restaurantdemo.repository.MenuRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final LoggerService logger;

    @Autowired
    public MenuService(MenuRepository menuRepository,LoggerService logger) {
        this.menuRepository = menuRepository;
        this.logger=logger;
    }

    public List<Menu> getAllMenus(){
        try {
            return menuRepository.findAll();
        }catch (Exception e){
            // Log the exception
            logger.error("Error while retrieving all manus", e.getMessage());
            throw new RuntimeException("Error while retrieving all menus", e);
        }
    }

    public Optional<Menu> getMenuById(Long id){
        try{
            return  menuRepository.findById(id);
        }catch(Exception e){
            logger.error("Error while retrieving menu by id",e.getMessage());
            throw  new RuntimeException("Error while retrieving menu by id",e);
        }
    }


    public Menu createMenu(Menu menu){
        try{
            return  menuRepository.save(menu);
        }catch (Exception e){
            logger.error("Error while creating menu",e.getMessage());
            throw  new RuntimeException("Error while creating menu",e);
        }
    }

    public void deleteMenu(Long id){
        try{
            menuRepository.deleteById(id);
        }catch (Exception e){
            logger.error("Error while deleting menu",e.getMessage());
            throw new RuntimeException("Error while deleting menu");
        }
    }


}
