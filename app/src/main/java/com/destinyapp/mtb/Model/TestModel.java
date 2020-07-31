package com.destinyapp.mtb.Model;

import com.google.gson.annotations.SerializedName;

public class TestModel {
    @SerializedName("id_produk")
    private String id_produk;
    @SerializedName("kode")
    private String kode;
    @SerializedName("namaproduk")
    private String namaproduk;
    @SerializedName("kategori")
    private String kategori;
    @SerializedName("harga")
    private String harga;
    @SerializedName("deskripsi")
    private String deskripsi;
    @SerializedName("foto")
    private String foto;
    @SerializedName("status")
    private String status;

    public TestModel(String id_produk, String kode, String namaproduk, String kategori, String harga, String deskripsi, String foto, String status){
        this.id_produk=id_produk;
        this.kode=kode;
        this.namaproduk=namaproduk;
        this.kategori=kategori;
        this.harga=harga;
        this.deskripsi=deskripsi;
        this.foto=foto;
        this.status=status;
    }

    public String getId_produk() {
        return id_produk;
    }

    public void setId_produk(String id_produk) {
        this.id_produk = id_produk;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNamaproduk() {
        return namaproduk;
    }

    public void setNamaproduk(String namaproduk) {
        this.namaproduk = namaproduk;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
