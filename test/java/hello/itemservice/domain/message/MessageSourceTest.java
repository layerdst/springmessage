package hello.itemservice.domain.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.test.context.TestExecutionListeners;

import java.util.Locale;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource messageSource;

    @Test
    void 헬로메세지(){
        String hello = messageSource.getMessage("hello", null, null);
        System.out.println(hello);

        Assertions.assertThat(hello).isEqualTo("안녕");
    }

    @Test
    void notFoundMessageCode() {
        Assertions.assertThatThrownBy(() -> messageSource.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void nooFoundMessageCodeDefaultMessage() {
        String result = messageSource.getMessage("no_code", null, "기본 메세지", null);
        Assertions.assertThat(result).isEqualTo("기본 메세지");
    }

    @Test
    void argumentMessage() {
        String message = messageSource.getMessage("hello.name", new Object[]{"Spring"}, null);
        Assertions.assertThat(message).isEqualTo("안녕Spring");
    }

    @Test
    void defaultMsg(){
        Assertions.assertThat(messageSource.getMessage("hello", null, null)).isEqualTo("안녕");
        Assertions.assertThat(messageSource.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
    }

    @Test
    void enLan() {
        Assertions.assertThat(messageSource.getMessage("hello", null, null, Locale.ENGLISH)).isEqualTo("hello");
    }



}
