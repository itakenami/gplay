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
        String tema = Cache.get(session.getId()+"-tema", String.class);
        if (tema == null) {
            tema = "redmond";
            Cache.set(session.getId()+"-tema", tema, "30mn");
        }
        renderArgs.put("tema", tema);
    }

    public static void changeTema(String tema){
        Cache.set(session.getId()+"-tema", tema, "30mn");
        redirect("/");
    }

}
