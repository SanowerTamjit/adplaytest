package com.santam.adplaytest.cotroller;

import com.google.gson.Gson;
import com.santam.adplaytest.service.DistrictstatsService;
import com.santam.adplaytest.service.TotalstatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class HomeController {


    private String appMode;

    @Autowired
    TotalstatsService totalstatsService;
    @Autowired
    DistrictstatsService districtstatsService;

    @Autowired
    public HomeController(Environment environment){
        appMode = environment.getProperty("app-mode");
    }

    @RequestMapping("/")
    public String index(Model model){

        Gson gson = new Gson();


        model.addAttribute("datetime", new Date());
        model.addAttribute("appname", "AdPlay-Technical-Test(Covid BD)");
        model.addAttribute("username", "Sanower Abedin Tamjit");
        model.addAttribute("mode", appMode);
        model.addAttribute("mongodb_totalstats", gson.toJson(totalstatsService.getTotalStatsMongo()));
        model.addAttribute("mongodb_districtstats", gson.toJson(districtstatsService.getDistrictStatsMongo()));
        model.addAttribute("mysql_totalstats", totalstatsService.getTotalStatsMysql());
        model.addAttribute("mysql_districtstats", districtstatsService.getDistrictStatsMysql());

        return "index";
    }

//    @GetMapping("/")
//    public String index(Model model) {
//        if (AppUtil.isAuthenticated())
//            return "userhome";
//        else
//            return "login";
//    }
//    @GetMapping("/login")
//    public String login(Model model) {
//        return "login";
//    }
//
//    @GetMapping("/home")
//    public String home(Model model) {
//        model.addAttribute("msgs", messageRepository.findAll());
////        return "userhome";
//        if (AppUtil.isAuthenticated())
//            return "userhome";
//        else
//            return "login";
//
//    }
//
//    @GetMapping("/products")
//    public String home1(Model model) {
//        model.addAttribute("msgs", messageRepository.findAll());
//        model.addAttribute("pdtlist", rainbowPdtInfoDao.findAll());
//        model.addAttribute("pdtcatlist", rainbowPdtCatDao.findAll());
//        return "products";
//    }
//
//
//
//    @GetMapping("/home2")
//    public String home2(Model model) {
//        model.addAttribute("msgs", messageRepository.findAll());
////        return "userhome";
//        return "dashboard";
//    }
//
//    @PostMapping("/messages")
//    public String saveMessage(Message message) {
//        messageRepository.save(message);
//        return "redirect:/home";
//    }

}