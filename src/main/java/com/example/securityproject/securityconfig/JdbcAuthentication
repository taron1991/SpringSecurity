@EnableWebSecurity
public class Configure extends WebSecurityConfigurerAdapter {

@Autowired
    BasicDataSource basicDataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(basicDataSource);
    }
}
