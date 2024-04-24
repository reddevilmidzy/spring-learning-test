package cholog;

import static cholog.utils.ContextUtils.getApplicationContext;
import static org.assertj.core.api.Assertions.assertThat;

import cholog.di.ConstructorInjection;
import cholog.di.FieldInjection;
import cholog.di.SetterInjection;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

class DependencyInjectionTest {
    @Test
    void constructorInjection() {
        final ApplicationContext context = getApplicationContext();
        final ConstructorInjection service = context.getBean("constructorInjection", ConstructorInjection.class);
        assertThat(service.sayHello()).isEqualTo("Hello");
    }

    @Test
    void setterInjection() {
        final ApplicationContext context = getApplicationContext();
        final SetterInjection service = context.getBean("setterInjection", SetterInjection.class);
        assertThat(service.sayHello()).isEqualTo("Hello");
    }

    @Test
    void autowiredInjection() {
        final ApplicationContext context = getApplicationContext();
        final FieldInjection service = context.getBean("fieldInjection", FieldInjection.class);
        assertThat(service.sayHello()).isEqualTo("Hello");
    }
}
