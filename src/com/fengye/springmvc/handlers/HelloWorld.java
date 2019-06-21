package com.fengye.springmvc.handlers;

import com.fengye.springmvc.beans.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

@SessionAttributes(value = {"user"},types = String.class)
@RequestMapping("/RequestClass")
@Controller
public class HelloWorld {
    private final static String SUCCESS="success";
/*
* 1.使用@RequestMapping 注解来映射请求的URL
* 2.返回值会通过视图解析器解析为实际的物理的视图，对于 InternalResourceViewResolver 视图解析器会做如下解析：
*   通过prefix +return Val +后缀 这样的方式得到实际的物理视图，然后做转发操作
*   /WEB-INF/views/+success+.jsp == /WEB-INF/views/success.jsp
*   <property name="prefix" value="/WEB-INF/views/"></property>
*   <property name="suffix" value=".jsp"></property>
* */
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("Hello World!");
        return SUCCESS;//跳转到success.jsp页面
    }
    @RequestMapping("/RequestMapping1")
    public String RequestMapping1(){
        System.out.println("RequestMapping修饰类");
        return SUCCESS;
    }
    @RequestMapping(value = "PostRequest",method = RequestMethod.POST)
    public String PostRequest(){
        System.out.println("Post请求！");
        return SUCCESS;
    }


    /*ant风格的资源地址支持3种匹配符*/
    /*
    * ?:匹配文件名中的一个字符
    * *:匹配文件名中的任意个字符
    * *:匹配多层路径
    * */

    @RequestMapping("test/*/RequestAnt")
    public String RequestAnt(){
        System.out.println("RequestAnt!");
         return SUCCESS;
    }

    /*
    * @PathVariable映射URL绑定的占位符
    *
    * */
    @RequestMapping("/TestPathVariable/{id}")
    public String TestPathVariable(@PathVariable("id") Integer id){
        System.out.println("TestPathVariable"+id);
        return SUCCESS;
    }
/*
* REST
*
* */

/*
* Rest 的 get 请求
* */
    @RequestMapping(value = "/TestRest/{id}",method = RequestMethod.GET)
    public String TestHiddenGet(@PathVariable Integer id){
        System.out.println("TestRest Get"+id);
        return SUCCESS;

    }
    /*
    * Rest 的 post请求
    * post请求是没有参数的
    * */

    @RequestMapping(value = "/TestRest",method = RequestMethod.POST)
    public String TestHiddenPost(){
        System.out.println("TestRest post");
        return SUCCESS;

    }
    /*
     * Rest 的 put请求
     * */
    @RequestMapping(value = "TestRest1/{id}",method = RequestMethod.PUT)
    public String TestHiddenPut(@PathVariable Integer id){
        System.out.println("TestRest put"+id);
        return SUCCESS;

    }
    /*
    * Rest 的 delete请求
    * */
    @RequestMapping(value = "TestRest2/{id}",method = RequestMethod.DELETE)
    public String TestHiddenDelete(@PathVariable Integer id){
        System.out.println("TestRest Delete"+id);
        return SUCCESS;

    }
    /*
    * @RequestParam来映射请求参数
    * @RequestParam(value = "age",required = false,defaultValue = "0")
    * value：即为请求参数的参数名
    * require 表示该参数是否必须 ，默认值为true
    * defaultValue 请求参数的默认值
    *
    * */

    @RequestMapping(value = "/testRequestParam")
    public String testRequestParam(@RequestParam(value = "username") String username,@RequestParam(value = "pwd") String pwd,
                                   @RequestParam(value = "age",required = false,defaultValue = "0") int age ){
        System.out.println("testRequestParam,username="+username+",pwd="+pwd+",age="+age);
        return SUCCESS;
    }

    /*
     * @CookieValue来映射请求参数
     * @RequestParam(value = "age",required = false,defaultValue = "0")
     * value：即为请求参数的参数名
     * require 表示该参数是否必须 ，默认值为true
     * defaultValue 请求参数的默认值
     *
     * */
    @RequestMapping("/CookiesValue")
    public String CookiesValue(@CookieValue(value = "JSESSIONID",required = true,defaultValue = "000") String sessionId){
        System.out.println("CookiesValue sessionId="+sessionId);
        return SUCCESS;
    }

    /*
    * Spring MVC 会按照请求参数名和POJO属性名进行自动的匹配
    * 自动的为该对象填充属性值，支持级联属性，
    * 如address.city/address.province等
    * */
    @RequestMapping("/testPOJO")
    public String testPOJO(User user){
        System.out.println("testPOJO"+user);
        return SUCCESS;
    }
    /*
    * SpringMVC 支持原生的 Servlet  的API作为目标方法的 参数
    * 具体支持以下类型
    * HttpServletResponse
    * HttpServletRequest
    * HttpSession
    * Principal
    * Locale
    * Writer
    * Reader
    * InputStream
    * outPutStream
    * */
    @RequestMapping("/Servlet")
    public void testServlet(HttpServletResponse response, HttpServletRequest request,Writer out){
        System.out.println("testPOJO response="+response+",request="+request);

        try {
            out.write("testPOJO response="+response+",request="+request);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return SUCCESS
    }

    /*
    * 目标方法的返回值可以是ModelAndView类型
    * 其中可以包含视图和模型信息
    * SpringMVC会把ModelAndView的model中的数据放入到request 域对象中。
    *
    * */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        String viewName=SUCCESS;
        ModelAndView modelAndView=new ModelAndView(viewName);
        //添加模型数据到ModelAndView 中
        modelAndView.addObject("time", new Date());


        return modelAndView;
    }
    /*
    *  目标方法可以添加Map 类型（实际上也可以是Model类型或ModelMap类型）的参数
    * */
    @RequestMapping("/testMap")
    public String testMap(Map<String,Object> map){
        System.out.println(map.getClass().getName());
        map.put("names", Arrays.asList("TOM","Jerry","Mike"));
        return SUCCESS;
    }

    /*@SessionAttributes
    * 默认情况下Spring MVC将模型中的数据存储到request域中。
    * 当一个请求结束后，数据就失效了。如果要跨页面使用。那么需要使用到session。
    * 而@SessionAttributes注解就可以使得模型中的数据存储一份到session域中。
    * 参数：
    * 1、names：这是一个字符串数组。里面应写需要存储到session中数据的名称。
    * 2、types：根据指定参数的类型，将模型中对应类型的参数存储到session中
    * 3、value：其实和names是一样的。
    *注意该注解只能放在类之上
    * */
    @RequestMapping("/testSessionAttribute")
    public String testSessionAttribute(Map<String,Object> map){
        User user=new User("Tom","123456","tom@qq.com",12);
        map.put("user",user);
        map.put("school","lantian");
        return SUCCESS;
    }

    /*
    * 执行流程
    * 1.执行@ModelAttribute注解修饰的方法：从数据库中取出对象，把对象放入到Map中，键为user
    * 2.SpringMVC 从Map中取出User对象，并把表单的请求参数赋值给该User对象的对应属性
    * 3.SpringMVC把上述对象传入目标方法的参数
    *
    * 注意：在@ModelAttribute修饰的方法中，放入到Map时的键需要和目标方法传入参数的 第一个字母小写的字符串一致
    *
    * */

    /**
     * 运行流程:
     * 1. 执行 @ModelAttribute 注解修饰的方法: 从数据库中取出对象, 把对象放入到了 Map 中. 键为: user
     * 2. SpringMVC 从 Map 中取出 User 对象, 并把表单的请求参数赋给该 User 对象的对应属性.
     * 3. SpringMVC 把上述对象传入目标方法的参数.
     *
     * 注意: 在 @ModelAttribute 修饰的方法中, 放入到 Map 时的键需要和目标方法入参类型的第一个字母小写的字符串一致!
     *
     * SpringMVC 确定目标方法 POJO 类型入参的过程
     * 1. 确定一个 key:
     * 1). 若目标方法的 POJO 类型的参数木有使用 @ModelAttribute 作为修饰, 则 key 为 POJO 类名第一个字母的小写
     * 2). 若使用了  @ModelAttribute 来修饰, 则 key 为 @ModelAttribute 注解的 value 属性值.
     * 2. 在 implicitModel 中查找 key 对应的对象, 若存在, 则作为入参传入
     * 1). 若在 @ModelAttribute 标记的方法中在 Map 中保存过, 且 key 和 1 确定的 key 一致, 则会获取到.
     * 3. 若 implicitModel 中不存在 key 对应的对象, 则检查当前的 Handler 是否使用 @SessionAttributes 注解修饰,
     * 若使用了该注解, 且 @SessionAttributes 注解的 value 属性值中包含了 key, 则会从 HttpSession 中来获取 key 所
     * 对应的 value 值, 若存在则直接传入到目标方法的入参中. 若不存在则将抛出异常.
     * 4. 若 Handler 没有标识 @SessionAttributes 注解或 @SessionAttributes 注解的 value 值中不包含 key, 则
     * 会通过反射来创建 POJO 类型的参数, 传入为目标方法的参数
     * 5. SpringMVC 会把 key 和 POJO 类型的对象保存到 implicitModel 中, 进而会保存到 request 中.
     *
     * 源代码分析的流程
     * 1. 调用 @ModelAttribute 注解修饰的方法. 实际上把 @ModelAttribute 方法中 Map 中的数据放在了 implicitModel 中.
     * 2. 解析请求处理器的目标参数, 实际上该目标参数来自于 WebDataBinder 对象的 target 属性
     * 1). 创建 WebDataBinder 对象:
     * ①. 确定 objectName 属性: 若传入的 attrName 属性值为 "", 则 objectName 为类名第一个字母小写.
     * *注意: attrName. 若目标方法的 POJO 属性使用了 @ModelAttribute 来修饰, 则 attrName 值即为 @ModelAttribute
     * 的 value 属性值
     *
     * ②. 确定 target 属性:
     * 	> 在 implicitModel 中查找 attrName 对应的属性值. 若存在, ok
     * 	> *若不存在: 则验证当前 Handler 是否使用了 @SessionAttributes 进行修饰, 若使用了, 则尝试从 Session 中
     * 获取 attrName 所对应的属性值. 若 session 中没有对应的属性值, 则抛出了异常.
     * 	> 若 Handler 没有使用 @SessionAttributes 进行修饰, 或 @SessionAttributes 中没有使用 value 值指定的 key
     * 和 attrName 相匹配, 则通过反射创建了 POJO 对象
     *
     * 2). SpringMVC 把表单的请求参数赋给了 WebDataBinder 的 target 对应的属性.
     * 3). *SpringMVC 会把 WebDataBinder 的 attrName 和 target 给到 implicitModel.
     * 近而传到 request 域对象中.
     * 4). 把 WebDataBinder 的 target 作为参数传递给目标方法的入参.
     */
    @RequestMapping("testModelAttribute")
    public String testModelAttribute(User user){
        System.out.println("修改："+user);
        return SUCCESS;
    }

    /*@ModelAttribute
    * 被@ModelAttribute标记的方法，会在每个目标方法执行之前被SpringMVC调用！
    * 即getUser方法中的map.put("user",user); "user"中的user必须和testModelAttribute(User user)方法参数中的user一直
    *
    * */

    @ModelAttribute
    public void getUser(@RequestParam(value = "id",required = false) Integer id,
                        Map<String,Object> map){
        if (id!=null){
            //模拟从数据库中获取对象
            User user=new User(1,"Tom","123456","tom@qq.com",16);
            System.out.println("从数据库中获取一个对象："+user);
            map.put("user",user);
        }
    }
    @RequestMapping("/testViewAndViewReolver")
    public String testViewAndViewReolver(){
        System.out.println("testViewAndViewReolver");
        return SUCCESS;
    }
    @RequestMapping("/testHelloView")
    public String testView(){
        System.out.println("testView");
        return "helloViews";
    }
    /*
    * 重定向 redirect
    * return "redirect:/result.jsp";
    * */
    @RequestMapping("/testRedirect")
    public String testRedirect(){
        System.out.println("testRedirect");
        return "redirect:/result.jsp";
    }
    /*
    * 转发 forward
     * return "forward:/result.jsp";
    * */
    @RequestMapping("/testForword")
    public String testForword(){
        System.out.println("testRedirect");
        return "forward:/result.jsp";
    }

}
