package xyz.douzhan.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * date: 2023/12/14 11:00
 *
 * @author 斗战圣洋
 * @since JDK 17
 */
@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping("print")
    public String print(){
        return "congrats you!";
    }
}
