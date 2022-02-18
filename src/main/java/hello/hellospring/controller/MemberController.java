package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import service.MemberService;
//컨트롤러 서비스 리파지토리 정형화
//1컨트롤러로 외부요청받고
//2서비스에서 비즈니스 로직을 만들고
//3레파지토리에서 데이터 생성
import java.util.List;
@Controller
public class MemberController {
    private final MemberService memberService;


    @Autowired

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