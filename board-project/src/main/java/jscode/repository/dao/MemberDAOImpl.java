package jscode.repository.dao;

import jscode.repository.dao.impl.MemberDAO;
import jscode.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberDAOImpl implements MemberDAO {

    @Override
    public Member login(Member member) {
        return null;
    }

    @Override
    public Member join(Member member) {
        return null;
    }

    @Override
    public Member logout(Member member) {
        return null;
    }
}
