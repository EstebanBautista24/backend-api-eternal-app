package com.eternal.api.Repository;

import com.eternal.api.Models.Prestamo;
import com.eternal.api.Models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPrestamoRepository extends JpaRepository<Prestamo, Long> {

    public List<Prestamo> findByEstadoTrue();
    public List<Prestamo> findByEstadoFalse();
    @Query("SELECT P FROM Prestamo  P ORDER BY  P.prestamoId DESC ")
    public List<Prestamo> obtenerOrdenado();

}
