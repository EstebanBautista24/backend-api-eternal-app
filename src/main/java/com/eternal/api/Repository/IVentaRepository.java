package com.eternal.api.Repository;

import com.eternal.api.Models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, Long> {

    @Query("SELECT SUM(v.ganancia) FROM Venta v WHERE v.fecha BETWEEN :fechaInicial AND :fechaFinal")
    public Double gananciaFecha(LocalDate fechaInicial, LocalDate fechaFinal);
}
