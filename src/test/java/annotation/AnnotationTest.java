/**
 * 
 */
package annotation;

import java.util.Set;

import javax.persistence.Entity;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import presentation.dto.PersonDto;
import entity.HommeDo;
import entity.PersonDo;

/**
 * @author romain
 *
 */
public class AnnotationTest {

  private Validator     validator;
  private static Logger log = Logger.getLogger(AnnotationTest.class);

  @Before
  public void init() {
    final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    this.validator = factory.getValidator();
  }

  @Test
  public void test() {
    final PojoAnnotationDo test = new PojoAnnotationDo();

    final Set<ConstraintViolation<PojoAnnotationDo>> ctv = validator.validate(test);
    for (final ConstraintViolation<PojoAnnotationDo> cv : ctv) {
      log.debug(cv.getRootBeanClass().getSimpleName() + " ." + cv.getPropertyPath() + " "
          + cv.getMessage());
    }

    Assert.assertTrue(ctv.isEmpty());
  }

  @Test
  public void PersonDoTest() {
    final PersonDo person = new HommeDo();

    final Set<ConstraintViolation<PersonDo>> ctv = validator.validate(person);
    for (final ConstraintViolation<PersonDo> cv : ctv) {
      log.debug(cv.getRootBeanClass().getSimpleName() + " ." + cv.getPropertyPath() + " "
          + cv.getMessage());
    }

    Assert.assertTrue(ctv.isEmpty());
  }

  @Test
  public void PersonDtoTest() {
    final PersonDto person = new PersonDto();

    final Set<ConstraintViolation<PersonDto>> ctv = validator.validate(person);
    for (final ConstraintViolation<PersonDto> cv : ctv) {
      log.debug(cv.getRootBeanClass().getSimpleName() + " ." + cv.getPropertyPath() + " "
          + cv.getMessage());
    }

    Assert.assertTrue(ctv.isEmpty());
  }

}

@Pojo
@Entity
class PojoAnnotationDo {

  private boolean b;
  private String  s;
  private int     i;

  public final boolean isB() {
    return b;
  }

  public final void setB(final boolean b) {
    this.b = b;
  }

  public final String getS() {
    return s;
  }

  public final void setS(final String s) {
    this.s = s;
  }

  public final int getI() {
    return i;
  }

  public final void setI(final int i) {
    this.i = i;
  }

}
