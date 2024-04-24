package cholog;

import static cholog.utils.ContextUtils.getApplicationContext;
import static org.assertj.core.api.Assertions.assertThat;

import cholog.bean.AutowiredBean;
import cholog.bean.SpringBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

class BeanTest {

    @Test
    void registerBean() {
        final ApplicationContext context = getApplicationContext();
        final SpringBean springBean = context.getBean("springBean", SpringBean.class);
        assertThat(springBean).isNotNull();
    }

    @Test
    void autowiredBean() {
        ApplicationContext context = getApplicationContext();
        AutowiredBean autowiredBean = context.getBean("autowiredBean", AutowiredBean.class);
        assertThat(autowiredBean.sayHello()).isEqualTo("Hello");
    }
}
