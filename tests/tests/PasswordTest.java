package tests;

import org.junit.Test;
import sample.entities.User;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Date: 15.02.2019 (пятница)
 * Project name: TestApplication
 * Package name: tests
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class PasswordTest {

    private static final String REGEXP = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^])(?=\\S+$).{6,}$";

    @Test
    public void testUser1() throws Exception {
        User user = new User();
        user.setLogin("login1");
        user.setPassword("passwoRrd@3123");
        user.setRole("Заказчик");
        user.setName("Иванов");

        assertTrue(user.getPassword().matches(REGEXP));
    }

    @Test
    public void testUser2() throws Exception {
        User user = new User();
        user.setLogin("login2");
        user.setPassword("badpassword0");
        user.setRole("Заказчик");
        user.setName("Иванов");

        assertFalse(user.getPassword().matches(REGEXP));
    }

    @Test
    public void testUser3() throws Exception {
        User user = new User();
        user.setLogin("login3");
        user.setPassword("GoOODpassword!0");
        user.setRole("Заказчик");
        user.setName("Иванов");

        assertTrue(user.getPassword().matches(REGEXP));
    }

    @Test
    public void testUser4() throws Exception {
        User user = new User();
        user.setLogin("login4");
        user.setPassword("G0$t");
        user.setRole("Менеджер");
        user.setName("Петров");

        assertFalse(user.getPassword().matches(REGEXP));
    }

    @Test
    public void testUser5() throws Exception {
        User user = new User();
        user.setLogin("login5");
        user.setPassword("G0$tik4");
        user.setRole("Менеджер");
        user.setName("Петров");

        assertTrue(user.getPassword().matches(REGEXP));
    }

    @Test
    public void testUser6() throws Exception {
        User user = new User();
        user.setLogin("login6");
        user.setPassword("DasdE$^21");
        user.setRole("Директор");
        user.setName("Коняев");

        assertTrue(user.getPassword().matches(REGEXP));
    }

    @Test
    public void testUser7() throws Exception {
        User user = new User();
        user.setLogin("login7");
        user.setPassword("kjkhhk8678%!");
        user.setRole("Менеджер");
        user.setName("Петров");

        assertFalse(user.getPassword().matches(REGEXP));
    }

    @Test
    public void testUser8() throws Exception {
        User user = new User();
        user.setLogin("login8");
        user.setPassword("FKDSLfsd@$%12");
        user.setRole("Кладовщик");
        user.setName("Петров");

        assertTrue(user.getPassword().matches(REGEXP));
    }

    @Test
    public void testUser9() throws Exception {
        User user = new User();
        user.setLogin("login9");
        user.setPassword("ASDQWEeqw!@#312");
        user.setRole("Менеджер");
        user.setName("Уткин");

        assertTrue(user.getPassword().matches(REGEXP));
    }

    @Test
    public void testUser10() throws Exception {
        User user = new User();
        user.setLogin("login10");
        user.setPassword("ASDASD2$#@!");
        user.setRole("Кладовщик");
        user.setName("Зинченко");

        assertTrue(user.getPassword().matches(REGEXP));
    }


}