package com.mycompany.ejercicio1;

import java.io.Serializable;

/**
 *
 * @author usuario
 */
public class Futbolista implements Serializable
{

    private short num;
    private String alias;
    private byte posicion;
    private float altura;
    private String equipo;

    public Futbolista()
    {
    }

    public Futbolista(short numero, String alias, byte posicion, float altura, String equipo)
    {
        this.num = numero;
        this.alias = alias;
        this.posicion = posicion;
        this.altura = altura;
        this.equipo = equipo;
    }

    public short getNumero()
    {
        return num;
    }

    public void setNumero(short numero)
    {
        this.num = numero;
    }

    public String getAlias()
    {
        return alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public byte getPosicion()
    {
        return posicion;
    }

    public void setPosicion(byte posicion)
    {
        this.posicion = posicion;
    }

    public float getAltura()
    {
        return altura;
    }

    public void setAltura(float altura)
    {
        this.altura = altura;
    }

    public String getEquipo()
    {
        return equipo;
    }

    public void setEquipo(String equipo)
    {
        this.equipo = equipo;
    }

    @Override
    public String toString()
    {
        return "Futbolista{" + "numero=" + num + ", alias=" + alias + ", posicion=" + posicion + ", altura=" + altura + ", equipo=" + equipo + '}';
    }

}
