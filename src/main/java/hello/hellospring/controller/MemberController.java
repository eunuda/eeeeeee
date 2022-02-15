package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import service.MemberService;
//컨트롤러 서비스 리파지토리... 정형화 ...
//1컨트롤러로 외부요청받고
//2서비스에서 비즈니스 로직을 만들고
//3레파지토리에서 데이터 생성
import java.util.List;

public class MemberController {

    //멤버서비스 스프링빈으로등록해주기
    private final MemberService memberService;

//    생성자에 @Autowired 가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다. 이렇게
//    객체 의존관계를 외부에서 넣어주는 것을 DI (Dependency Injection), 의존성 주입이라 한다.
//    이전 테스트에서는 개발자가 직접 주입했고, 여기서는 @Autowired에 의해 스프링이 주입해준다

    @Autowired
    //멤버 컨트롤러가 실행이될때 빈에 등록되있는 멤버서비스 객체를 갖다가 넣어줌 ..디벤턴시 인젝션
    //의존관계성 .. 이게 오토와이어
    public MemberController(MemberService memberService) {

        this.memberService = memberService;
    }

    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";

    }


    @PostMapping(value = "/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/";
    }
//회원 컨트롤러에서 조회 기능
    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";

    }
}