/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucnm.listener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Minh Phuc
 */
public class RoadmapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        FileReader fr = null;
        String line = null;
        ServletContext context = sce.getServletContext();

        try {
            HashMap<String, String> roadmap = new HashMap<String, String>();

            String realPath = sce.getServletContext().getRealPath("/") + "WEB-INF/roadmap.txt";
            fr = new FileReader(realPath);
            BufferedReader br = new BufferedReader(fr);

            try {
                while ((line = br.readLine()) != null) {
                    String split[] = line.split("=");

                    String src = split[0];
                    String dst = split[1];

                    if (src.equals("")) {
                        src = "default";
                    }

                    roadmap.put(src, dst);
                }
            } catch (IOException ex) {
                context.log("RoadmapListener _ IO " + ex.getMessage());
            }

            br.close();
            fr.close();

            sce.getServletContext().setAttribute("ROADMAP", roadmap);
        } catch (FileNotFoundException ex) {
            context.log("RoadmapListener _ FileNotFound " + ex.getMessage());

        } catch (IOException ex) {
            context.log("RoadmapListener _ IO " + ex.getMessage());
        } finally {

        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
