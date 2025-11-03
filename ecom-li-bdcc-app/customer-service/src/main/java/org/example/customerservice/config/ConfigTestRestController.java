package org.example.customerservice.config;


import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RefreshScope
public class ConfigTestRestController {
    private final CustomerConfigParams customerConfigParams;

    public ConfigTestRestController(CustomerConfigParams customerConfigParams) {
        this.customerConfigParams = customerConfigParams;
    }

    @GetMapping("/testConfig")
    public CustomerConfigParams configTest(){
        return customerConfigParams;
    }
}
