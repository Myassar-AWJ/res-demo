package com.restaurant.restaurantdemo.service;


import com.restaurant.restaurantdemo.model.Menu;
import com.restaurant.restaurantdemo.repository.MenuRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class MenuService {

    private final MenuRepository menuRepository;
    private static final Logger logger = LoggerFactory.getLogger(MenuService.class);

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> getAllMenus(){
        try {
            return menuRepository.findAll();
        }catch (Exception e){
            // Log the exception
            logger.error("Error while retrieving all manus", e);
            throw new RuntimeException("Error while retrieving all menus", e);
        }
    }

    public Optional<Menu> getMenuById(Long id){
        try{
            return  menuRepository.findById(id);
        }catch(Exception e){
            logger.error("Error while retrieving menu by id",e);
            throw  new RuntimeException("Error while retrieving menu by id",e);
        }
    }


    public Menu createMenu(Menu menu){
        try{
            return  menuRepository.save(menu);
        }catch (Exception e){
            logger.error("Error while creating menu",e);
            throw  new RuntimeException("Error while creating menu",e);
        }
    }

    public void deleteMenu(Long id){
        try{
            menuRepository.deleteById(id);
        }catch (Exception e){
            logger.error("Error while deleting menu");
            throw new RuntimeException("Error while deleting menu");
        }
    }


}
