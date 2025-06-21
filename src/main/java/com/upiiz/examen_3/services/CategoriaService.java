package com.upiiz.examen_3.services;

import com.upiiz.examen_3.models.CategoriaModel;
import com.upiiz.examen_3.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Service
public class CategoriaService implements CategoriaRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<CategoriaModel> findAllCategorias() {
        return jdbcTemplate.query("SELECT * FROM categorias",
                new BeanPropertyRowMapper<>(CategoriaModel.class));
    }

    @Override
    public CategoriaModel findCategoriaById(int id) {
        return jdbcTemplate.query("SELECT * FROM categorias WHERE id=?",
                        new BeanPropertyRowMapper<>(CategoriaModel.class),id)
                .stream().findFirst().orElse(new CategoriaModel());
    }

    @Override
    public CategoriaModel save(CategoriaModel categoria) {
        KeyHolder keyHolder = new GeneratedKeyHolder(); // Para capturar el ID generado

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO categorias(nombre) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS // Indicar que queremos recuperar las claves generadas
            );
            ps.setString(1, categoria.getNombre());
            return ps;
        }, keyHolder);

        // Obtenemos el ID generado y lo asignamos al producto
        Number generatedId = keyHolder.getKey();
        if (generatedId != null) {
            categoria.setId(generatedId.longValue());
        }else {
            categoria.setId(0L);
        }

        return categoria; // Retornamos el modelo con el ID asignado
    }

    @Override
    public int update(CategoriaModel categoria) {
        int registrosAfectados=jdbcTemplate.update(
                "UPDATE categorias SET nombre=? WHERE id=?",
                categoria.getNombre(), categoria.getId());
        return registrosAfectados;
    }

    @Override
    public int delete(int id) {
        int registrosAfectados=jdbcTemplate.update("DELETE FROM categorias WHERE id=?",id);
        return registrosAfectados;
    }

}
