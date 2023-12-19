package com.iftm.cassandraredis.entities.dto;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.iftm.cassandraredis.entities.Imovel;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class ImovelDTO implements Serializable {
    private UUID id;
    private String endereco;
    private String tipoImovel;
    private double area;
    private double preco;

    public ImovelDTO() {
        this.id = Uuids.timeBased();
    }

    public ImovelDTO(String endereco, String tipoImovel, double area, double preco) {
        this.endereco = endereco;
        this.tipoImovel = tipoImovel;
        this.area = area;
        this.preco = preco;
    }

    public ImovelDTO(UUID id, String endereco, String tipoImovel, double area, double preco) {
        this.id = id;
        this.endereco = endereco;
        this.tipoImovel = tipoImovel;
        this.area = area;
        this.preco = preco;
    }

    public ImovelDTO(Imovel imovel) {
        if (imovel.getId() != null){
            this.id = imovel.getId();
        }
        this.endereco = imovel.getEndereco();
        this.tipoImovel = imovel.getTipoImovel();
        this.area = imovel.getArea();
        this.preco = imovel.getPreco();
    }

    public Imovel toImovel(){
        UUID id = null;
        if (this.id != null){
            id = this.id;
        }
        return new Imovel(id, this.endereco,this.tipoImovel,this.area,this.preco);
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
        ImovelDTO imovelDTO = (ImovelDTO) o;
        return Double.compare(imovelDTO.area, area) == 0 && Double.compare(imovelDTO.preco, preco) == 0 && Objects.equals(id, imovelDTO.id) && Objects.equals(endereco, imovelDTO.endereco) && Objects.equals(tipoImovel, imovelDTO.tipoImovel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, endereco, tipoImovel, area, preco);
    }

    @Override
    public String toString() {
        return "ImovelDTO{" +
                "id=" + id +
                ", endereco='" + endereco + '\'' +
                ", tipoImovel='" + tipoImovel + '\'' +
                ", area=" + area +
                ", preco=" + preco +
                '}';
    }
}
