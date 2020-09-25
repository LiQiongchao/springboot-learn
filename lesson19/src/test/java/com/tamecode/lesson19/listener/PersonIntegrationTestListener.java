package com.tamecode.lesson19.listener;

import com.tamecode.lesson19.domain.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * @author Liqc
 * @date 2020/9/25 17:35
 */
public class PersonIntegrationTestListener extends AbstractTestExecutionListener {

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
        ApplicationContext applicationContext = testContext.getApplicationContext();
        Person person = applicationContext.getBean("primaryPerson", Person.class);
        System.err.println("person: " + person);
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        System.err.println("before: " + testContext.getTestMethod());
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        System.err.println("after: " + testContext.getTestMethod());
    }

    @Override
    public int getOrder() {
        return 1999;
    }
}
