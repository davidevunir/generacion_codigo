package com.msa.historiasUsu_Resp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan("com.msa.historiasUsu_Resp.repository.model")
@EnableJpaRepositories("com.msa.historiasUsu_Resp.repository.jpa")

public class MsaHistoriasUsu_RespApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsaHistoriasUsu_RespApplication.class, args);
    }
}
