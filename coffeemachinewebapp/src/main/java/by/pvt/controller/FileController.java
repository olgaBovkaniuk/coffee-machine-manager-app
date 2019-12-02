package by.pvt.controller;

import by.pvt.service.FileService;
import by.pvt.service.ImageSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
@RequestMapping("/files")
public class FileController {

    private static Logger log = Logger.getLogger("FileController");

    @Autowired
    FileService fileService;

    @GetMapping("/coffee-machine/{coffeeMachineId}")
    public void getFile(HttpServletResponse response,
                        @PathVariable UUID coffeeMachineId,
                        @RequestParam(value = "size", required = false) ImageSize size) throws Exception {
//        log.info("getFile is calling...");
        File file = fileService.getFileBySize(coffeeMachineId, size);

        // Content-Type
        // application/pdf
        response.setContentType("image/gif");

        // Content-Disposition
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName());

        // Content-Length
        response.setContentLength((int) file.length());

        BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());

        inStream.transferTo(outStream);
        outStream.flush();
        outStream.close();
        inStream.close();
    }
}
