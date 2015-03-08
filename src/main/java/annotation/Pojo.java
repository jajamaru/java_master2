/**
 * 
 */
package annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author romain
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PojoValidator.class)
@Documented
public @interface Pojo {

  String message() default "{pojo.message.error}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
