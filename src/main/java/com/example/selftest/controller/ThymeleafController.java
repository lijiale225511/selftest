package com.example.selftest.controller;

import com.example.selftest.dto.UserDTO;
import com.example.selftest.service.LoginService;
import com.example.selftest.utils.ExportWord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

@RestController
@RequestMapping(value = "/api/template")
public class ThymeleafController {

    private final LoginService loginService;
    private final ExportWord exportWord;

    @Autowired(required = true)
    public ThymeleafController(LoginService loginService, ExportWord exportWord) {
        this.loginService = loginService;
        this.exportWord = exportWord;
    }

    private final Logger logger = LoggerFactory.getLogger(ThymeleafController.class);

    /**
     * @Description: 模板示例
     * @Author: SN
     * @Date: 2020/11/31 11:02
     */
    @RequestMapping(path = "/thymeleaf", method = RequestMethod.GET)
    public ModelAndView getThymeleaf(Model model) {
        try {
            UserDTO userDTO = loginService.getByLoginCode("admin");
            model.addAttribute("user", userDTO);
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return new ModelAndView("default", "userModel", model);
    }

    /**
     * @Description: Word文档导出
     * @Author: SN
     * @Date: 2020/11/31 11:02
     */
    @RequestMapping(value = "/exportWord", method = RequestMethod.POST)
    public void exportWord(HttpServletResponse response) {
        try {
            // 得到导出文档
            File outFile = exportWord.createTemplate();
            InputStream fin = new FileInputStream(outFile);
            //输出Word文档
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/msword");
            OutputStream out = response.getOutputStream();
            byte[] buffer = new byte[512];  // 缓冲区
            int bytesToRead = -1;
            // 通过循环将读入的Word文件的内容输出到浏览器中
            while ((bytesToRead = fin.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }
            out.close();
            fin.close();
            if (!outFile.delete()) {
                System.out.println(outFile.getName() + "删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
