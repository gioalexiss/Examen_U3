package com.upiiz.examen_3.repositories;

import com.upiiz.examen_3.models.CategoriaModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository {
    public List<CategoriaModel> findAllCategorias();
    public CategoriaModel findCategoriaById(int id);
    public CategoriaModel save(CategoriaModel categoria);
    public int update(CategoriaModel categoria);
    public int delete(int id);
}
