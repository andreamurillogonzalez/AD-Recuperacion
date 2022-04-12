package com.mycompany.ejercicio01;

import java.io.Serializable;

/**
 *
 * @author usuario
 */
enum Puesto{
    PORTERO, DEFENSA, CENTROCAMPISTA, DELANTERO
}

public class Futbolista implements Serializable{
    private int identificador;
    private String alias;
    private String codigo;
    private Puesto puesto;
    private float Altura;

    public Futbolista()
    {
    }

    public Futbolista(int identificador, String alias, String codigo, int puesto, float Altura)
    {
        this.identificador = identificador;
        this.alias = alias;
        this.codigo = codigo;
        this.puesto = definirPuesto(puesto);
        this.Altura = Altura;
    }

    public int getIdentificador()
    {
        return identificador;
    }

    public void setIdentificador(int identificador)
    {
        this.identificador = identificador;
    }

    public String getAlias()
    {
        return alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public String getCodigo()
    {
        return codigo;
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    public Puesto getPuesto()
    {
        return puesto;
    }

    public void setPuesto(int puesto)
    {
        this.puesto = definirPuesto(puesto);
    }

    public float getAltura()
    {
        return Altura;
    }

    public void setAltura(float Altura)
    {
        this.Altura = Altura;
    }

    @Override
    public String toString()
    {
        return "Futbolista{" + "identificador=" + identificador + ", alias=" + alias + ", codigo=" + codigo + ", puesto=" + puesto + ", Altura=" + Altura + '}';
    }
    
    public Puesto definirPuesto(int p){
        Puesto puesto = Puesto.PORTERO;
        switch(p){
            case 1:
                puesto = Puesto.PORTERO;
                break;
            case 2:
                puesto = Puesto.DEFENSA;
               break;
            case 3:
                puesto = Puesto.CENTROCAMPISTA;
                break;
            case 4:
                puesto = Puesto.DELANTERO;
                break;
        }
        return puesto;
    }
}
