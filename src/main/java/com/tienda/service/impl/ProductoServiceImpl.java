package com.tienda.service.impl;

import com.tienda.dao.ProductoDao;
import com.tienda.domain.Producto;
import com.tienda.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoDao productoDao;
            
    @Override
    @Transactional(readOnly = true)
    public List<Producto> getProductos(boolean activos) {
        var lista = productoDao.findAll();
        // Si activos es true, se deben pasar solo la productos activas
        
        if (activos){
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }
    @Override
    
    //Este busca el objeto que coincida si lo encuentra todo bien y si no devuelve null
    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }

    @Override
    
    //Si el obejto tiene definido un idproducto y si no viene con el id hace un insert
    @Transactional
    public void save(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    
    //busca un objeto con idproducto y si lo encuentra lo borra
    @Transactional
    public void delete(Producto producto) {
        productoDao.delete(producto);
    }


    
}