package com.upiiz.examen_3.repositories;

import com.upiiz.examen_3.models.CategoriaModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository {
    public List<CategoriaModel> findAllCategorias();
    public CategoriaModel findCategoriaById(int id);
    //Regresa la mascota, incluyendo el id
    public CategoriaModel save(CategoriaModel categoria);

    //Regresa la cantidad de registros afectados= 1, 30 o 0
    public int update(CategoriaModel categoria);
    //Regrese la cantidad de mascotas eliminadas = 5 o 0
    public int delete(int id);
}
