package org.light32.pd.utils.file.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * class level annotation for a class that can be mapped to a record
 *
 * @author jwhitt 8/27/16
 */
@Target(ElementType.TYPE)
public @interface FlatFile {
    String fileId();
}
