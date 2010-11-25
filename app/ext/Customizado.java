/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ext;

import play.templates.JavaExtensions;

/**
 *
 * @author itakenami
 */
public class Customizado extends JavaExtensions {

    public static String customizeTag(String val) {
        return "("+val+")";
    }
}
