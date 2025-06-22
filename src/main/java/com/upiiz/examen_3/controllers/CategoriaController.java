package com.upiiz.examen_3.controllers;

import com.upiiz.examen_3.models.CategoriaModel;
import com.upiiz.examen_3.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
@Controller
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/")
    public String categorias() {

        return "categorias";
    }

    @GetMapping("/v1/api/categorias")
    public ResponseEntity<Map<String,Object>> getAllCategorias() {
        List<CategoriaModel> categorias=categoriaService.findAllCategorias();
        return ResponseEntity.ok(Map.of(
                "estado",1,
                "mensaje","Listado de categorias",
                "categorias",categorias
        ));
    }

    @PostMapping("/v1/api/categorias")
    public ResponseEntity<Map<String,Object>> categoriaPost(@RequestBody Map<String,Object> objetoCategoria) {
        CategoriaModel categoria = new CategoriaModel(
                objetoCategoria.get("nombre").toString()
        );
        CategoriaModel categoriaGuardada=categoriaService.save(categoria);
        if(categoriaGuardada!=null)
            return ResponseEntity.ok(Map.of(
                    "estado",1,
                    "mensaje","Categoria guardada correctamente",
                    "categoria", categoriaGuardada
            ));
        else
            return ResponseEntity.ok(Map.of(
                    "estado",0,
                    "mensaje","Error: No se pudo guardar la categoria",
                    "categoria", objetoCategoria
            ));
    }

    @PostMapping("/v1/api/categorias/eliminar")
    public ResponseEntity<Map<String,Object>> categoriaDelete(
            @RequestBody Map<String,Object> objetoCategoria) {

        int id = Integer.parseInt(objetoCategoria.get("id").toString());

        if(categoriaService.delete(id) > 0){
            return ResponseEntity.ok(Map.of(
                    "estado",1,
                    "mensaje","Categoria eliminada"
            ));
        }else {
            return ResponseEntity.ok(Map.of(
                    "estado",0,
                    "mensaje","No se pudo eliminar la categoria"
            ));
        }
    }

    @GetMapping("/v1/api/categorias/actualizar/{id}")
    public ResponseEntity<Map<String,Object>> categoriaActualizar(@PathVariable int id) {
        CategoriaModel categoria = categoriaService.findCategoriaById(id);
        return ResponseEntity.ok(Map.of(
                "estado",1,
                "mensaje","Categoria encontrada",
                "categoria", categoria
        ));
    }

    @PostMapping("/v1/api/categorias/actualizar/{id}")
    public ResponseEntity<Map<String,Object>> categoriaActualizarDatos(@PathVariable Long id, @RequestBody Map<String,Object> objetoCategoria) {
        CategoriaModel categoria = new CategoriaModel(
                objetoCategoria.get("nombre").toString()
        );
        categoria.setId(id);
        if(categoriaService.update(categoria) > 0)
            return ResponseEntity.ok(Map.of(
                    "estado",1,
                    "mensaje","Categoria actualizada correctamente",
                    "categoria", categoria
            ));
        else
            return ResponseEntity.ok(Map.of(
                    "estado",0,
                    "mensaje","Error: No se pudo actualizar la categoria",
                    "categoria", objetoCategoria
            ));
    }
}
