package hr.tvz.gal.hardwareapp.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


//https://www.baeldung.com/javax-validations-enums
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EnumNameValidator.class)
public @interface EnumName {
    String regexp();
    String message() default "must match \"{regexp}\"";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
