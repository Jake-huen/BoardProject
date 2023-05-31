package jscode.repository.dao.impl;

import jscode.domain.Member;

public interface MemberDAO {

    Member login(Member userAccount);

    Member join(Member userAccount);

    Member logout(Member userAccount);
}
