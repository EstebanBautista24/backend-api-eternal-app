package com.eternal.api.Models;

import com.eternal.api.Models.DTO.Register.RegistrarPrestamo;
import com.eternal.api.Models.DTO.Update.ActualizarPrestamo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prestamoId;
    private LocalDate fecha;
    private String prestamoNombre;
    private Boolean estado;
    private Boolean prestado;
    private Double prestamoValor;
    private Double deudaTotal;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "transaccionId")
    private Transaccion transaccion;
    @OneToMany(mappedBy = "prestamo")
    private List<PagoPrestamo> pagos;

    public Prestamo(RegistrarPrestamo registrarPrestamo){
        pagos = new ArrayList<>();
        this.estado = true;
        this.prestamoNombre = registrarPrestamo.getPrestamoNombre();
        this.fecha = registrarPrestamo.getFecha();
        this.prestamoValor = registrarPrestamo.getValor();
        this.prestado = registrarPrestamo.getPrestado();
        this.deudaTotal = registrarPrestamo.getValor();
    }

    public void actualizar(ActualizarPrestamo actualizarPrestamo){
        if(actualizarPrestamo.getPrestamoValor()!=null){
            if(Objects.equals(this.prestamoValor, this.deudaTotal)){
                this.deudaTotal = actualizarPrestamo.getPrestamoValor();
                System.out.println("case 1");
            }
            else if(actualizarPrestamo.getPrestamoValor()<this.getDeudaTotal()){
                this.deudaTotal = actualizarPrestamo.getPrestamoValor();
                System.out.println("case 2");
            }
            else{
                this.deudaTotal +=actualizarPrestamo.getPrestamoValor()-this.prestamoValor;
                System.out.println("case 3");
            }

            this.prestamoValor=actualizarPrestamo.getPrestamoValor();


        }
        if(actualizarPrestamo.getFecha()!=null){
            this.fecha = actualizarPrestamo.getFecha();
        }
    }
}
