package hello.hellospring;


import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.MemberService;

import javax.sql.DataSource;

@Configuration
//회원서비스와 회원리포지토리의 @service, @repository , @autowired 애노테이션을 제거하고 진행한다
public class SpringConfig {

    private final DataSource dataSource;
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
        @Bean
        public MemberService memberService() {
            return new MemberService(memberRepository());

        }
        @Bean
        public MemberRepository memberRepository() {

            return new MemoryMemberRepository();
        }

}

//참고: 실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용한다.
//그리고 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다
//주의: @Autowired 를 통한 DI는 helloConroller , memberService 등과 같이 스프링이 관리하는 객체에서만 동작한다.
// 스프링 빈으로 등록하지 않고 내가 직접 생성한 객체에서는 동작하지 않는다
