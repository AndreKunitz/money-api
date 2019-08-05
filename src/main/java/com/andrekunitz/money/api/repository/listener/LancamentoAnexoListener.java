package com.andrekunitz.money.api.repository.listener;

import com.andrekunitz.money.api.MoneyApiApplication;
import com.andrekunitz.money.api.model.Lancamento;
import com.andrekunitz.money.api.storage.S3;
import org.springframework.util.StringUtils;

import javax.persistence.PostLoad;

public class LancamentoAnexoListener {

    @PostLoad
    public void postLoad(Lancamento lancamento) {
        if (StringUtils.hasText(lancamento.getAnexo())) {
            S3 s3 = MoneyApiApplication.getBean(S3.class);
            lancamento.setUrlAnexo(s3.configurarUrl(lancamento.getAnexo()));
        }
    }
}
