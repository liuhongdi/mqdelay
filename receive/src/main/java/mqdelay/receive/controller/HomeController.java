package mqdelay.receive.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {
    //初始化并发送消息
    @RequestMapping("/home")
    public String send() throws Exception {
        return "this is receive home ";
    }
}
