package service;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Service;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;
@Service
//멤버서비스는 스프링컨피그에서 관리를 하고있지만 든게 없어서 오토와이어 만먹음
public class MemberService {
  private final MemberRepository memberRepository;
   public MemberService(MemberRepository memberRepository) {

       this.memberRepository = memberRepository;
    }
   // 참고: 생성자에 @Autowired 를 사용하면 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서
   // 주입한다. 생성자가 1개만 있으면 @Autowired 는 생략할 수 있다 . 생성자에는 오토와이어 안써도됨

        /**
         * 회원가입
         */

            public Long join(Member member) {
            validateDuplicateMember(member); //중복 회원 검증
            memberRepository.save(member);
            return member.getId();

        }
        private void validateDuplicateMember(Member member) {
            memberRepository.findByName(member.getName())
                    .ifPresent(m -> {
                        throw new IllegalStateException("이미 존재하는 회원입니다.");
                    });
        }
        /**
         * 전체 회원 조회
         */
        public List<Member> findMembers() {

            return memberRepository.findAll();
        }
        public Optional<Member> findOne(Long memberId) {

            return memberRepository.findById(memberId);
        }
    }


