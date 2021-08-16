package business_logic;

import org.springframework.stereotype.Service;

@Service
public class SomeBean {

    private final SomeOtherBean someOtherBean;

    public SomeBean(SomeOtherBean someOtherBean) {
        this.someOtherBean = someOtherBean;
    }

    public String getData() {
        return this.someOtherBean.getData();
    }

}
