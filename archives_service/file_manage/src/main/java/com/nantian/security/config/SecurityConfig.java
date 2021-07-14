/*
package com.nantian.security.config;

//import com.nantian.security.provider.SpringSecurityProvider;
//import com.nantian.security.userservice.CustomUserService;
//import com.nantian.security.userservice.MyFilterSecurityInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

//import javax.annotation.Resource;
//import javax.sql.DataSource;

//@Configuration
//   //开启 spring  Secutiry 的功能@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)   //开启注解控制权限
public class SecurityConfig   extends WebSecurityConfigurerAdapter {


//    @Resource
//    private SpringSecurityProvider provider;

    @Resource
    private DataSource  dataSource;

//    @Autowired
//    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;




//    @Resource
//    private AuthenticationSuccessHandler securityAuthenticationSuccessHandler;

//    @Resource
//    private AuthenticationFailureHandler securityAuthenticationFailHandler;

*/
/*    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       //请求授权的规则
        http.authorizeRequests()
                .antMatchers("/login").permitAll() // 不需要权限路径
                .anyRequest().authenticated()
                .and()
                .formLogin()    //没有权限自动跳转到登录页
                .loginPage("/login")    // 登入页面
//                .successHandler(securityAuthenticationSuccessHandler)  //自定义成功处理器
//                .failureHandler(securityAuthenticationFailHandler)     //自定义失败处理器
                .permitAll()
                .and()
                .logout();
                //开启了注销功能
                http.logout();

        super.configure(http);
    }*//*



  */
/*  //认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).
                withUser("zhangyan").password(new BCryptPasswordEncoder().encode("123456")).roles("user");

        //后期获取数据从数据获取
//        auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema().withUser("admin").password("123456").roles("admin");
    }*//*


    */
/* @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
//      auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").
//      withUser("admin").password("password").roles("USER", "ADMIN");
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("user").
              password(new BCryptPasswordEncoder().encode("password")).roles("USER");
//        builder.authenticationProvider(provider);
    }*//*




 */
/*   // 加密方式
    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return  new CustomUserService();
    }*//*



   */
/* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoderBean()); //user Details Service验证


    }*//*




    */
/**
     * 定义哪些URL被保护、哪些URL不被保护
     * @param http
     * @throws Exception
     *//*


*/
/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO Auto-generated method stub
        http.authorizeRequests()
                .antMatchers("/login").permitAll() // 不需要权限路径
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")    // 登入页面
                .successHandler(securityAuthenticationSuccessHandler)  //自定义成功处理器
                .failureHandler(securityAuthenticationFailHandler)     //自定义失败处理器
                .permitAll()
                .and()
                .logout();

    }*//*


*/
/*@Override
protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .anyRequest().authenticated() //任何请求,登录后可以访问
            .and()
            .formLogin()
            .loginPage("/login")
            .failureUrl("/login?error")
            .permitAll() //登录页面用户任意访问
            .and()
            .logout().permitAll(); //注销行为任意访问
//    http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
}*//*


}
*/
