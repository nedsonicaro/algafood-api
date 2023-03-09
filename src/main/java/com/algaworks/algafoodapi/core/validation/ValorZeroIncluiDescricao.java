package com.algaworks.algafoodapi.core.validation;
import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = { ValorZeroIncluiDescricaoValidator.class })
public @interface ValorZeroIncluiDescricao {
    String message() default "descrição obrigatória inválida";
    Class<?>[] groups() default { };
    Class<? extends javax.validation.Payload>[] payload() default { };
    String valorField();
    String descricaoField();
    String descricaoObrigatoria();
}
