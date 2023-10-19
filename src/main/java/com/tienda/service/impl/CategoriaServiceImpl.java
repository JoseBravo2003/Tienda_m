package com.tienda.service.impl;

import com.tienda.dao.CategoriaDao;
import com.tienda.domain.Categoria;
import com.tienda.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
public class CategoriaServiceImpl implements CategoriaService{

    @Autowired
    private CategoriaDao categoriaDao;
            
    @Override
    @Transactional(readOnly = true)
    public List<Categoria> getCategorias(boolean activos) {
        var lista = categoriaDao.findAll();
        // Si activos es true, se deben pasar solo la categorias activas
        
        if (activos){
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }
    @Override
    
    //Este busca el objeto que coincida si lo encuentra todo bien y si no devuelve null
    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria categoria) {
        return categoriaDao.findById(categoria.getIdCategoria()).orElse(null);
    }

    @Override
    
    //Si el obejto tiene definido un idcategoria y si no viene con el id hace un insert
    @Transactional
    public void save(Categoria categoria) {
        categoriaDao.save(categoria);
    }

    @Override
    
    //busca un objeto con idcategoria y si lo encuentra lo borra
    @Transactional
    public void delete(Categoria categoria) {
        categoriaDao.delete(categoria);
    }


    
}