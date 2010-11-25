/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import play.cache.Cache;
import play.mvc.Before;
import play.mvc.Controller;

/**
 *
 * @author itakenami
 */
public class DefaultController extends Controller {

    @Before
    public static void setTema() {
        String tema = Cache.get("tema", String.class);
        if (tema == null) {
            tema = "redmond";
            Cache.set("tema", tema, "30mn");
        }
        renderArgs.put("tema", tema);
    }

    public static void changeTema(String tema){
        Cache.set("tema", tema, "30mn");
        Projetos.index();
    }
}
