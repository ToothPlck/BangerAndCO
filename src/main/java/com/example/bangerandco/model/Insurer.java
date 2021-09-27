package com.example.bangerandco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "insurance")
public class Insurer implements Serializable {
    @Id
    @Column(name = "license")
    private String license;

    public Insurer() {
    }

    public Insurer(String license) {
        this.license = license;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}
