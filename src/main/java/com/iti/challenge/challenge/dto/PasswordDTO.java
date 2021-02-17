package com.iti.challenge.challenge.dto;

import java.util.Objects;

public class PasswordDTO {
    private String senha;

    public PasswordDTO() {
    }

    public PasswordDTO(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasswordDTO that = (PasswordDTO) o;
        return Objects.equals(senha, that.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senha);
    }

    @Override
    public String toString() {
        return "PasswordDTO{" +
                "senha='" + senha + '\'' +
                '}';
    }
}