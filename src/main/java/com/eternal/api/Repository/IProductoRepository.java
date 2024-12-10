package com.eternal.api.Repository;

import com.eternal.api.Models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
    public List<Producto> findByEstadoFalse();
    public List<Producto> findByEstadoTrue();
    @Query("SELECT P FROM Producto  P ORDER BY  P.productoId DESC ")
    public List<Producto> obtenerOrdenado();

}
