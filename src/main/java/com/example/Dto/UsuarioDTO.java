package com.example.Dto;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioDTO {
    
    private String user;
    private String senha;
    private String token;

    public UsuarioDTO(String user, String senha) {
        this.user = user;
        setSenha(senha);
    }


    public void setSenha(String senha) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            byte messageDisgest[] = md.digest(senha.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDisgest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            this.senha = hexString.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(UsuarioDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean validaSenha(String senha){
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            byte messageDisgest[] = md.digest(senha.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDisgest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            String senhaDigitada = hexString.toString();
            return senhaDigitada.equals(this.senha);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(UsuarioDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}