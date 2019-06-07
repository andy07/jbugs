package msg.filters;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface StarkPermissions {

    public enum Permission{
        USER_MANAGEMENT,
        BUG_MANAGEMENT,
        BUG_CLOSE,
        BUG_EXPORT_PDF,
        USERUL_ADRESAT,
        PERMISSION_MANAGEMENT
    }

    Permission[] permissions();

}
