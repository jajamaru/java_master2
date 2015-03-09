/**
 * 
 */
package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

/**
 * @author romain
 *
 */
public class PojoValidator implements ConstraintValidator<Pojo, Object> {

  private static final String   GETTER_PREFIX         = "get";
  private static final String   SETTER_PREFIX         = "set";
  private static final String   GETTER_BOOLEAN_PREFIX = "is";
  private static final String   DO_SUFFIXE            = "Do";
  private static final String   DTO_SUFFIXE           = "Dto";

  private static final String   PACKAGE_SEPARATOR     = "\\.";

  private static final String[] PROCESSING_ALLOWED    = { "equals", "hashCode", "toString" };

  @Override
  public void initialize(final Pojo pojo) {
    // No value to retrieve
  }

  @Override
  public boolean isValid(final Object object, final ConstraintValidatorContext cv) {
    if (object == null) {
      return true;
    }

    final Class<?> cl = object.getClass();

    final boolean validCompleteName = isOnlyClass(cl) && isValidName(cl.getSimpleName())
        && isValidPackageName(cl);
    final boolean validDoWithEntity = doOnlyWithEntity(cl);
    final boolean validProcessing = noProcessing(cl.getDeclaredMethods());

    if (!validAttributeProperties(cl, cv)) {
      addErrorMessageProperties("{pojo.message.error.attribut.private}", cv);
      addErrorMessageProperties("{pojo.message.error.attribut.getter}", cv);
      addErrorMessageProperties("{pojo.message.error.getter.public}", cv);
      return false;
    }

    if (!validCompleteName) {
      addErrorMessageProperties("{pojo.message.error.name}", cv);
    }
    if (!validProcessing) {
      addErrorMessageProperties("{pojo.message.error.processing}", cv);
    }
    if (!validDoWithEntity) {
      addErrorMessageProperties("{pojo.message.error.missing.entity}", cv);
    }

    return validCompleteName && validDoWithEntity && validProcessing;
  }

  private boolean validAttributeProperties(final Class<?> cl, final ConstraintValidatorContext cv) {
    final Set<Method> gettersAndSetters = getGettersAndSetters(cl.getDeclaredMethods());
    final boolean validAttributes = allPrivateAttributs(cl.getDeclaredFields());
    final boolean validAllAttributes = gettersAndSettersForAll(cl.getDeclaredFields(),
        gettersAndSetters);
    final boolean validPublicGetter = gettersAndSettersArePublic(gettersAndSetters);
    return validAttributes && validAllAttributes && validPublicGetter;
  }

  private void addErrorMessageProperties(final String message, final ConstraintValidatorContext cv) {
    cv.buildConstraintViolationWithTemplate(message).addConstraintViolation();
  }

  private boolean isValidName(final String name) {
    return name.length() > 1 && (name.endsWith(DO_SUFFIXE) || name.endsWith(DTO_SUFFIXE))
        && Character.isUpperCase(name.charAt(0));
  }

  private boolean isValidPackageName(final Class<?> clazz) {
    if (clazz.getPackage() == null) {
      return false;
    }

    final String packages = clazz.getName().substring(0,
        clazz.getName().length() - clazz.getSimpleName().length() - 1);
    return StringUtils.isAllLowerCase(StringUtils.join(packages.split(PACKAGE_SEPARATOR), ""));
  }

  private boolean isOnlyClass(final Class<?> clazz) {
    return !(clazz.isAnnotation() || clazz.isInterface() || clazz.isEnum());
  }

  private boolean doOnlyWithEntity(final Class<?> clazz) {
    if (clazz.getSimpleName().endsWith(DO_SUFFIXE)) {
      for (final Annotation a : clazz.getAnnotations()) {
        if (a instanceof Entity) {
          return true;
        }
      }
      return false;
    }
    return true;
  }

  private boolean allPrivateAttributs(final Field[] fields) {
    for (final Field itField : fields) {
      if (!(isConstant(itField) || isPrivateAttribute(itField))) {
        return false;
      }
    }
    return true;
  }

  private boolean isPrivateAttribute(final Field field) {
    return Modifier.isPrivate(field.getModifiers());
  }

  private boolean isConstant(final Field field) {
    return Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers())
        && Modifier.isFinal(field.getModifiers());
  }

  private Set<Method> getGettersAndSetters(final Method[] methods) {
    final Set<Method> getterAndSetter = new HashSet<Method>();
    for (final Method m : methods) {
      if (m.getName().startsWith(GETTER_PREFIX) || m.getName().startsWith(SETTER_PREFIX)
          || m.getName().startsWith(GETTER_BOOLEAN_PREFIX)) {
        getterAndSetter.add(m);
      }
    }
    return getterAndSetter;
  }

  private boolean gettersAndSettersArePublic(final Collection<Method> gettersAndSetters) {
    for (final Method m : gettersAndSetters) {
      if (!Modifier.isPublic(m.getModifiers())) {
        return false;
      }
    }
    return true;
  }

  private boolean gettersAndSettersForAll(final Field[] fields,
      final Collection<Method> gettersAndSetters) {

    final Set<String> gettersAndSettersName = getNameOfMethodList(gettersAndSetters);

    for (final Field f : fields) {
      final String comparedName = Character.toUpperCase(f.getName().charAt(0))
          + f.getName().substring(1);
      if (f.getType() == Boolean.class || f.getType() == boolean.class) {
        if (!getterAndSetterForBoolean(f, gettersAndSettersName, comparedName)) {
          return false;
        }
      } else {
        if (!getterAndSetterForOther(f, gettersAndSettersName, comparedName)) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean getterAndSetterForBoolean(final Field field,
      final Collection<String> gettersAndSettersName, final String comparedName) {
    return isConstant(field)
        || isValidGetterOrSetter(gettersAndSettersName, GETTER_BOOLEAN_PREFIX + comparedName,
            SETTER_PREFIX + comparedName);
  }

  private boolean getterAndSetterForOther(final Field field,
      final Collection<String> gettersAndSettersName, final String comparedName) {
    return isConstant(field)
        || isValidGetterOrSetter(gettersAndSettersName, GETTER_PREFIX + comparedName, SETTER_PREFIX
            + comparedName);
  }

  private Set<String> getNameOfMethodList(final Collection<Method> methods) {
    final Set<String> methodsName = new HashSet<String>();
    for (final Method m : methods) {
      methodsName.add(m.getName());
    }
    return methodsName;
  }

  private boolean isValidGetterOrSetter(final Collection<String> collection, final String prefixe,
      final String suffixe) {
    return collection.contains(prefixe) && collection.contains(suffixe);
  }

  private boolean noProcessing(final Method[] methods) {
    final List<String> processingAllowed = Arrays.asList(PROCESSING_ALLOWED);
    for (final Method m : methods) {
      if (!(m.getName().startsWith(GETTER_BOOLEAN_PREFIX) || m.getName().startsWith(GETTER_PREFIX) || m
          .getName().startsWith(SETTER_PREFIX)) && !processingAllowed.contains(m.getName())) {
        return false;
      }
    }
    return true;
  }

}
