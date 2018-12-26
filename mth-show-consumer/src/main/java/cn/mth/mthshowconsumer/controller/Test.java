package cn.mth.mthshowconsumer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

@RequestMapping("/a")
    public String getall(){
    return "看看看看";
}
}
