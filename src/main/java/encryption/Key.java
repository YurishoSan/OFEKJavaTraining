package encryption;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by yurisho on 10/08/2016.
 *
 * Object representing a key.
 *
 * @author Yitzhak Goldstein
 * @version 1.0
 */
@Data
public class Key implements Serializable {
    private char key;

    public Key(char key) {
        this.key = key;
    }
}
