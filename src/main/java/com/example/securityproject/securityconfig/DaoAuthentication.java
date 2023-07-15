@EnableWebSecurity
public class Configure extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/sec/admin").hasAuthority("dev:write")
                .antMatchers("/sec/client").hasAnyAuthority("dev:read","dev:write")
                .antMatchers("/sec/all").permitAll()
                .and().formLogin().permitAll();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setPasswordEncoder(passwordEncoder());
        dao.setUserDetailsService(userDetailsService);
        return dao;
    }



    //with SecurityFilterChain(WE DON'T NEED TO extend WebSecurityConfigurerAdapter!!)
     /**@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
       return security.authorizeRequests()
               .antMatchers("/sec/admin").hasAuthority("write")
               .antMatchers("/sec/client").hasAnyAuthority("read","write")
               .antMatchers("/sec/all").permitAll()
               .and().formLogin().permitAll()
               .and().build();
    }**/
}
