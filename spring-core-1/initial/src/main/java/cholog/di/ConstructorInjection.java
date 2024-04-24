package cholog.di;

import org.springframework.stereotype.Service;

@Service
public class ConstructorInjection {
    private final InjectionBean injectionBean;

    /*
    ConstructorInjection으로 InjectionBean 주입받기
     */
    public ConstructorInjection(final InjectionBean injectionBean) {
        this.injectionBean = injectionBean;
    }

    public String sayHello() {
        return injectionBean.hello();
    }
}
