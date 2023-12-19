package com.iftm.cassandraredis.entities;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Objects;
import java.util.UUID;
@Table(value = "imovel")
public class Imovel {
    @Id
    @PrimaryKey
    private UUID id;
    @Column
    private String endereco;
    @Column(value = "tipo_imovel")
    private String tipoImovel;
    @Column
    private double area;
    @Column
    private double preco;

    public Imovel() {
        this.id = Uuids.timeBased();
    }

    public Imovel(String endereco, String tipoImovel, double area, double preco) {
        this.endereco = endereco;
        this.tipoImovel = tipoImovel;
        this.area = area;
        this.preco = preco;
    }

    public Imovel(UUID id, String endereco, String tipoImovel, double area, double preco) {
        this.id = id;
        this.endereco = endereco;
        this.tipoImovel = tipoImovel;
        this.area = area;
        this.preco = preco;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipoImovel() {
        return tipoImovel;
    }

    public void setTipoImovel(String tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Imovel imovel = (Imovel) o;
        return Double.compare(imovel.area, area) == 0 && Double.compare(imovel.preco, preco) == 0 && Objects.equals(id, imovel.id) && Objects.equals(endereco, imovel.endereco) && Objects.equals(tipoImovel, imovel.tipoImovel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, endereco, tipoImovel, area, preco);
    }

    @Override
    public String toString() {
        return "Imovel{" +
                "id=" + id +
                ", endereco='" + endereco + '\'' +
                ", tipoImovel='" + tipoImovel + '\'' +
                ", area=" + area +
                ", preco=" + preco +
                '}';
    }
}
