package com.eternal.api.Repository;

import com.eternal.api.Models.PagoPrestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPagoPrestamoRepository extends JpaRepository<PagoPrestamo, Long> {
}
