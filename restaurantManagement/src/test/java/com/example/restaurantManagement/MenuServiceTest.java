package com.example.restaurantManagement;
import com.example.restaurantManagement.model.Menu;
import com.example.restaurantManagement.repository.MenuRepository;
import com.example.restaurantManagement.serviceLayer.MenuServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private MenuServiceImplementation menuService;

    private Menu menuItem1;
    private Menu menuItem2;

    @BeforeEach
    void setUp() {
        menuItem1 = new Menu();
        menuItem1.setId(3);
        menuItem1.setName("Noodles");
        menuItem1.setPrice(100);

        menuItem2 = new Menu();
        menuItem2.setId(4);
        menuItem2.setName("Burger");
        menuItem2.setPrice(100);
    }

    @Test
    void testSaveMenu() {
        when(menuRepository.save(menuItem1)).thenReturn(menuItem1);

        Menu savedMenu = menuService.saveMenu(menuItem1);

        assertNotNull(savedMenu);
        assertEquals("Noodles", savedMenu.getName());
        verify(menuRepository, times(1)).save(menuItem1);
    }

    @Test
    void testSaveMenus() {
        List<Menu> menuList = Arrays.asList(menuItem1, menuItem2);
        when(menuRepository.saveAll(menuList)).thenReturn(menuList);

        List<Menu> savedMenus = menuService.saveMenus(menuList);

        assertNotNull(savedMenus);
        assertEquals(2, savedMenus.size());
        verify(menuRepository, times(1)).saveAll(menuList);
    }

    @Test
    void testUpdateMenu() {
        when(menuRepository.findById(1)).thenReturn(Optional.of(menuItem1));

        Menu updatedMenu = new Menu();
        updatedMenu.setName("Veg Noodles");
        updatedMenu.setPrice(99);

        Menu result = menuService.updateMenu(1, updatedMenu);

        assertNotNull(result);
        assertEquals("Veg Noodles", result.getName());
        assertEquals(99, result.getPrice());
        verify(menuRepository, times(1)).save(menuItem1);
    }

    @Test
    void testUpdateMenu_NotFound() {
        when(menuRepository.findById(3)).thenReturn(Optional.empty());

        Menu updatedMenu = new Menu();
        updatedMenu.setName("Veg Pizza");
        updatedMenu.setPrice(120);
        Menu result = menuService.updateMenu(3, updatedMenu);
        assertNull(result);
        verify(menuRepository, never()).save(any());
    }

    @Test
    void testGetFromMenu() {
        when(menuRepository.findById(1)).thenReturn(Optional.of(menuItem1));

        Menu foundMenu = menuService.getFromMenu(1);

        assertNotNull(foundMenu);
        assertEquals("Noodles", foundMenu.getName());
        verify(menuRepository, times(1)).findById(1);
    }

    @Test
    void testGetFromMenu_NotFound() {
        when(menuRepository.findById(7)).thenReturn(Optional.empty());

        Menu result = menuService.getFromMenu(7);

        assertNull(result);
        verify(menuRepository, times(1)).findById(7);
    }
}
