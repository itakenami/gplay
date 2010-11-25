/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package validator;

import models.Cargo;
import play.data.validation.Check;

/**
 *
 * @author itakenami
 */
public class Quantidade extends Check {

    @Override
    public boolean isSatisfied(Object o, Object o1) {
        Cargo val = (Cargo)o;
        return val.nome.length() > 3;
    }

}
