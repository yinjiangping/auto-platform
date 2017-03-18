package team.yqby.platform.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@RestController
public class SpringBootController {

    @RequestMapping("/")
    public ModelAndView page1() {
       return new ModelAndView("login");
    }

}
