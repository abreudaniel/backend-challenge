package com.iti.challenge.challenge.service;

import com.google.common.base.Preconditions;
import com.iti.challenge.challenge.dto.PasswordDTO;
import com.iti.challenge.challenge.validate.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PasswordService {

    final static int PASSWORD_LIMIT_LENGHT = 9;
    private final Logger logger = LoggerFactory.getLogger(PasswordService.class);

    /**
     * Metodo para validas as valdações da senha como:
     * ° Nove ou mais caracteres
     * ° Ao menos 1 d?gito.
     * ° Ao menos 1 letra minuscula.
     * ° Ao menos 1 letra maiuscula.
     * ° Ao menos 1 caractere especial: !@#$%^&*()-+
     * ° Não permitir caracteres repetidos
     * @param passwordDTO
     * @return Retornar true caso todas as contições sejam verdadeiras e false caso não seja valida
     * */
    public boolean valid(PasswordDTO passwordDTO){
        logger.info("Validando o senha");
        boolean valueExpression = passwordDTO.getSenha()  != null && !passwordDTO.getSenha().isEmpty();
        Preconditions.checkArgument(valueExpression, "false");
        Preconditions.checkArgument(passwordDTO.getSenha().length() >= PASSWORD_LIMIT_LENGHT, "false");

        if(Validate.isValid(passwordDTO.getSenha(),Validate.Type.CHECK_PARAMETER)){
            logger.info("Validado senha com todas as condicoes sem a validacao da repeticao");
            if(Validate.isValid(passwordDTO.getSenha(),Validate.Type.CHECK_REPEAT_CARACTER_NUMBER_SPECIAL)){
                logger.info("Invalidado senha com repeticao de caracteres");
                throw new RuntimeException("false");
            }
            return true;

        }else{
            logger.info("Invalido para todas as condicoes");
            throw new RuntimeException("false");
        }
    }

}
