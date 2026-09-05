package alquilerVehiculos;

import exception.*;

import java.util.regex.Pattern;

public abstract class Vehiculo {
    private String matricula;
    private boolean alquilado;

    public Vehiculo(String matricula) {
        if (comprobarMatricula(matricula)) {
            this.matricula = matricula;
        }
        this.alquilado = false;
    }

    public static boolean comprobarMatricula(String matricula) {
        if (!Pattern.matches("^[A-Z]{1,2}\\d{4}[A-Z]{1,2}$", matricula) &&
        !Pattern.matches("^\\d{4}[B-DF-HJ-NPR-TV-Z]{3}$", matricula)) {
            throw new MatriculaException("Matricula invalida");
        }
        
        return true;
    }

    public abstract void alquilar(int dato);
    public abstract void devolver(int dato);

    public boolean estaAlquilado() {return alquilado;}

    public void mostrar() {
        System.out.print("Matricula: " + matricula + "\tAlquilado: " + alquilado + "\t");
    }

    public boolean equals(Object obj) {
        if (obj instanceof Vehiculo) {
            Vehiculo newVehiculo = (Vehiculo) obj;
            return newVehiculo.matricula.equals(matricula);
        }
        return false;
    }

    public void setAlquilado(boolean alquilado) {
        this.alquilado = alquilado;
    }

    public String getMatrcula() {return matricula;}
}
