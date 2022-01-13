package com.example.resourceserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class GUIController {
    @Value("${gui.location}")
    private String guiLocation;

    @GetMapping(value = {"/", ""})
    public String home(HttpServletResponse httpResponse) {
        File requestedFile = new File(guiLocation + File.separator + "/index.html");
        if (requestedFile.exists()) {
            try {
                httpResponse.sendRedirect("/gui");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            try {
                httpResponse.sendRedirect("/view/home");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    @GetMapping(value = {
            "/gui", "/gui/**"
    })
    @ResponseBody
    public ResponseEntity actions(HttpServletRequest httpRequest) throws IOException {
        String requestUrl = httpRequest.getRequestURL().toString();
        if (
                requestUrl.endsWith(".js")
                        || requestUrl.endsWith(".png")
                        || requestUrl.endsWith(".css")
                        || requestUrl.endsWith(".woff2")
                        || requestUrl.endsWith(".woff")
                        || requestUrl.endsWith(".tff")
                        || requestUrl.endsWith(".ico")
        ) {
            String[] paths = requestUrl.split("/");

            String filename = paths[paths.length - 1];
            StringBuilder newPath = new StringBuilder(guiLocation);
            for (int i = 4; i < paths.length; i++) {
                newPath.append(File.separator);
                if (i == (paths.length - 1)) break;
                newPath.append(paths[i]);
            }
            newPath.append(filename);
            File requestedFile = new File(newPath.toString());
            if (requestedFile.exists()) {
                FileInputStream fis = new FileInputStream(requestedFile);
                return ResponseEntity.ok(new InputStreamResource(fis));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            File requestedFile = new File(guiLocation + File.separator + "/index.html");

            if (requestedFile.exists()) {
                FileInputStream fis = new FileInputStream(requestedFile);
                return ResponseEntity.ok(new InputStreamResource(fis));
            } else {
                return ResponseEntity.notFound().build();
            }

        }

    }

    public static InputStream getResourceFileAsInputStream(String fileName) {
        ClassLoader classLoader = GUIController.class.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }
}
