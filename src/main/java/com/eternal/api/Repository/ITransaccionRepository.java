package com.eternal.api.Repository;

import com.eternal.api.Models.TipoTransaccion;
import com.eternal.api.Models.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ITransaccionRepository extends JpaRepository<Transaccion, Long> {
    @Query("select t from Transaccion t order by t.transaccionId desc")
    List<Transaccion> obtenerOrdenado();
    List<Transaccion> findByTipo(TipoTransaccion tipo);
    @Query("SELECT SUM(t.valor) FROM Transaccion t WHERE (t.fecha BETWEEN :fechaInicial AND :fechaFinal) AND t.tipo='GASTO'")
    Double gastoMes(LocalDate fechaInicial, LocalDate fechaFinal);
}
