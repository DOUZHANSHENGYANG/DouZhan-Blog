package xyz.douzhan.blog;

import cn.hutool.crypto.SmUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

import java.nio.charset.StandardCharsets;

/**
 * Description:
 * date: 2023/12/14 20:56
 *
 * @author 斗战圣洋
 * @since JDK 17
 */
@SpringBootTest
public class BTest {
    @Test
    public void testSM4() {
        System.out.println(SmUtil.sm4().encryptBase64("082811LSY#", StandardCharsets.UTF_8));
    }

}
