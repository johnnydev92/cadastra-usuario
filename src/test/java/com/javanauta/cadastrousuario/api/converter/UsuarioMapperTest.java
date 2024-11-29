package com.javanauta.cadastrousuario.api.converter;


import com.javanauta.cadastrousuario.api.response.EnderecoResponseDTO;
import com.javanauta.cadastrousuario.api.response.EnderecoResponseDTOFixture;
import com.javanauta.cadastrousuario.api.response.UsuarioResponseDTO;
import com.javanauta.cadastrousuario.api.response.UsuarioResponseDTOFixture;
import com.javanauta.cadastrousuario.infrastructure.entities.EnderecoEntity;
import com.javanauta.cadastrousuario.infrastructure.entities.UsuarioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UsuarioMapperTest {


    UsuarioMapper usuarioMapper;

    UsuarioEntity usuarioEntity;

    EnderecoEntity enderecoEntity;

    UsuarioResponseDTO usuarioResponseDTO;

    EnderecoResponseDTO enderecoResponseDTO;

    LocalDateTime dataHora;


    @BeforeEach
    public void setup(){

        usuarioMapper = Mappers.getMapper(UsuarioMapper.class);
        dataHora = LocalDateTime.of(2023,10, 05, 14, 12, 15);

        enderecoEntity = EnderecoEntity.builder()
                .rua("Rua Teste")
                .bairro("Bairro Teste")
                .cep("9875588900")
                .cidade("Cidade Teste")
                .numero(1452L)
                .complemento("Casa 1").build();

        usuarioEntity = UsuarioEntity.builder().
                id(124L).
                nome("Usuario")
                .documento("12345678")
                .email("usuario@email.com")
                .dataCadastro(dataHora)
                .endereco(enderecoEntity).build();


        enderecoResponseDTO = EnderecoResponseDTOFixture.build
                ("Rua Teste",
                1452L,
                "Bairro Teste",
                "Casa 1",
                "Cidade Teste",
                "9875588900");

        usuarioResponseDTO = UsuarioResponseDTOFixture.build(
                124L,
                "Usuario",
                "usuario@email.com",
                "12345678",
                enderecoResponseDTO);


    }

    @Test
    void deveConverterParaUsuarioResponseDTO(){

       UsuarioResponseDTO dto = usuarioMapper.paraUsuarioResponseDTO(usuarioEntity);

        assertEquals(usuarioResponseDTO, dto);
    }
}
