package hello.hellospring;


import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.MemberService;

@Configuration
    public class SpringConfig {
    //멤버 서비스랑 , 멤버리포지토리를 스프링빈에 등록을하고
    // 스프링빈에 등록된애들을 멤버리포지토리르르 멤버서비스에 넣어준다

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
