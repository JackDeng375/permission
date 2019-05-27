package com.jack.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jack.exception.ParamException;
import org.apache.commons.collections.MapUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

public class BeanValidator {
    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public static <T> Map<String, String> validate(T t, Class... groups) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> validateResult = validator.validate(t, groups);
        if (validateResult.isEmpty()) {
            return Collections.EMPTY_MAP;
        } else {
            //判断是否有错误信息
            //有错误信息就记录
            LinkedHashMap errors = Maps.newLinkedHashMap();
            Iterator<ConstraintViolation<T>> iterator = validateResult.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation violation = iterator.next();
                errors.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            return errors;
        }
    }

    public static Map<String, String> validateList(Collection<?> collection) {
        //判断集合是否为空
        Preconditions.checkNotNull(collection);
        Iterator<?> iterator = collection.iterator();
        Map errors;
        do {
            if (!iterator.hasNext()) {
                //说明没有值
                return Collections.EMPTY_MAP;
            }
            Object object = iterator.next();
            errors = validate(object, new Class[0]);
        } while (errors.isEmpty());
        return errors;
    }

    public static Map<String, String> validateObject(Object first, Object... objects) {
        //判断验证集合
        if (objects != null && objects.length > 0) {
            return validateList(Lists.asList(first, objects));
        } else {
            //验证单个对象
            return validate(first, new Class[0]);
        }
    }

    public static void check(Object param) throws ParamException {
        Map<String, String> map = BeanValidator.validateObject(param);
        if (MapUtils.isNotEmpty(map)) {
            throw new ParamException(map.toString());
        }
    }

}