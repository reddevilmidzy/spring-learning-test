package cholog;

import static cholog.utils.ContextUtils.getApplicationContext;
import static org.assertj.core.api.Assertions.assertThat;

import cholog.scan.ComponentScanBean;
import cholog.scan.ContextConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

class ComponentScanTest {

    @Test
    void scanComponent() {
        final ApplicationContext context = getApplicationContext(ContextConfiguration.class);
        final ComponentScanBean componentScanBean = context.getBean("componentScanBean", ComponentScanBean.class);
        assertThat(componentScanBean).isNotNull();
    }
}
