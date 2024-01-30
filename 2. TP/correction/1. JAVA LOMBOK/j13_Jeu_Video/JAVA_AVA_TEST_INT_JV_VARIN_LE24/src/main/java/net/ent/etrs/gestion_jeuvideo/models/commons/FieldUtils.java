package net.ent.etrs.gestion_jeuvideo.models.commons;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Pour les fields
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FieldUtils {
    public static  <T> List<Field> listAllFields(T obj) {
        List<Field> fieldList = new ArrayList<Field>();
        Class<?> tmpClass = obj.getClass();
        while (tmpClass != null) {
            fieldList.addAll(Arrays.asList(tmpClass.getDeclaredFields()));
            tmpClass = tmpClass.getSuperclass();
        }
        return fieldList;
    }
}
