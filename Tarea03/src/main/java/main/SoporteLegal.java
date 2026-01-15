package main;

import chainofresponsibility.Incident;

public class SoporteLegal extends Usuario {
    private String especialidad;

    public SoporteLegal(String id, String nombre, String email, String telefono, String especialidad) {
        super(id, nombre, email, telefono);
        this.especialidad = especialidad;
    }

    public void resolverCasoLegal(Incident incident) {
        System.out.println("Soporte Legal (" + especialidad + ") resolviendo caso: " + incident.getDescription());
    }

    public String getEspecialidad() {
        return especialidad;
    }
}
