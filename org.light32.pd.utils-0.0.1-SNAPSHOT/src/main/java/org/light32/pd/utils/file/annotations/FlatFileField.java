package org.light32.pd.utils.file.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * annotation marking a flat file field
 * place on getters for the field in question
 *
 * @author jwhitt 8/27/16
 */
@Target(ElementType.METHOD)
public @interface FlatFileField {
    String name();

    int position();

    int width();
}
