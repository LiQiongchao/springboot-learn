package com.tamecode.lesson19;

import com.tamecode.lesson19.configuration.PersonConfiguration;
import com.tamecode.lesson19.domain.Person;
import com.tamecode.lesson19.listener.PersonIntegrationTestListener;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 *
 *
 * @ContextHierarchy 注入测试需要的依赖，如 PersonIntegrationTest，其它的content hierarchies则不加载
 *
 * @RunWith(SpringRunner.class) 以Junit的环境运行，并加载了SpringBoot的环境
 *
 * @TestExecutionListeners
 * @SpringBootTest 等同于 @ContextHierarchy
 *  多个 listener 执行优先级的问题
 *      listeners 值中是以定义的先后顺序执行的。
 *      当使用 spring.factories 管理的时候，是以 getOrder 的值来决定执行的先后顺序的,
 *          参考： org\springframework\spring-test\5.2.7.RELEASE\spring-test-5.2.7.RELEASE.jar!\META-INF\spring.factories
 *
 * @author Liqc
 * @date 2020/9/25 16:53
 */
@RunWith(SpringRunner.class)
@ContextHierarchy(value = @ContextConfiguration(classes = {PersonConfiguration.class}))
/*@TestExecutionListeners(listeners = {
        PersonIntegrationTestListener.class,
        DependencyInjectionTestExecutionListener.class
})*/
@TestPropertySource(properties = {"name = monkey"})
public class PersonIntegrationTest {

    @Autowired
    private Person person;

    @Value(value = "${name}")
    private String name;

    @BeforeClass
    public static void beforeClass() {
        System.err.println("beforeClass");
    }

    @AfterClass
    public static void afterClass() {
        System.err.println("afterClass");
    }

    @Before
    public void before() {
        System.err.println("before");
    }

    @After
    public void after() {
        System.err.println("after");
    }

    @Test
    public void primaryPersonTest() {
        Assert.assertEquals(Long.valueOf(1), person.getId());
        Assert.assertEquals("gaga", person.getName());
        Assert.assertEquals(Integer.valueOf(23), person.getAge());
    }

    @Test
    public void nameTest() {
        Assert.assertEquals("monkey", name);
    }

}
