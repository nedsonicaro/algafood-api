package com.algaworks.algafoodapi.core.jackson;

import com.algaworks.algafoodapi.api.controller.model.mixin.CidadeMixin;
import com.algaworks.algafoodapi.api.controller.model.mixin.CozinhaMixin;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

        private static final long serialVersionUID = 1L;

        public JacksonMixinModule() {
            setMixInAnnotation(Cidade.class, CidadeMixin.class);
            setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
        }

}
