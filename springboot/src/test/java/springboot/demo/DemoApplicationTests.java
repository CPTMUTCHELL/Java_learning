package springboot.demo;

import example.DemoApplication;
import example.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
class DemoApplicationTests {
    @Autowired
    @BeforeEach
    public void initDb(){
        User user=new User();
        user.setLogin("Hi").setPass("Hi").setId(3);

    }
    @Test
    public void testUser() {
    }


}
