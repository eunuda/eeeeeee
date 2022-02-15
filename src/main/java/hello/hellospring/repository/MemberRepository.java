package hello.hellospring.repository;


import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
//바꿔 끼울 인터페이스
    //아직 데이터 저장소가 선정되지 않아서 무선 인터페이스로 구현 클래스를 변경할수있도록 설계
    //데이터는 다양한 저장소를 고민중인  상황으로 가정
            //개발을 진행하기위해서는 구현을 가볍게 인터페이스에 만듬

    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

}

